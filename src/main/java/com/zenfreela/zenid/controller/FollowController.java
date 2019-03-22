package com.zenfreela.zenid.controller;

import com.zenfreela.zenid.model.Follow;
import com.zenfreela.zenid.model.Profile;
import com.zenfreela.zenid.repository.FollowRepository;
import com.zenfreela.zenid.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private FollowRepository followRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public FollowController(FollowRepository followRepository,
                            ProfileRepository profileRepository) {
        this.followRepository = followRepository;
        this.profileRepository = profileRepository;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Flux<Follow>> findAll() {
        return ok(followRepository.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Flux<Follow>> findByEmail(@PathVariable("email") @NonNull String email) {
        Profile profile = profileRepository.findById(email).block();
        if (profile == null) {
            return notFound().build();
        }

        List<Follow> followers = profile.getFollowers();
        if (followers.size() == 0) {
            return noContent().build();
        }

        Flux<Follow> followFlux = Flux.fromArray(
                followers.toArray(new Follow[0])
        );

        return ok(followFlux);
    }

    @PatchMapping(path = "/{email}/{follow}", produces = "application/json")
    public ResponseEntity<Mono<Follow>> followUser(@PathVariable("email") @NonNull String email,
                                                   @PathVariable("follow") @NonNull String follow) {

        Profile profile = profileRepository.findById(email).block();
        if (profile == null) {
            return notFound().build();
        }

        Follow follower = Follow.builder()
                .email(follow)
                .build();

        Mono<Follow> followMono = followRepository.save(follower);
        profile.getFollowers().add(follower);

        profileRepository.save(profile);

        return ok(followMono);
    }

    @DeleteMapping(path = "/{email}/{follow}", produces = "application/json")
    public ResponseEntity<Mono<Void>> unfollowUser(@PathVariable("email") @NonNull String email,
                                                   @PathVariable("follow") @NonNull String follow) {

        Profile profile = profileRepository.findById(email).block();
        if (profile == null) {
            return notFound().build();
        }

        Follow follower = followRepository.findById(follow).block();
        if (follower == null) {
            return notFound().build();
        }

        Mono<Void> followMono = followRepository.delete(follower);
        profile.getFollowers().remove(follower);

        profileRepository.save(profile);

        return ok(followMono);
    }

}