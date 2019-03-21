package com.zenfreela.zenid.controller;

import com.zenfreela.zenid.model.Profile;
import com.zenfreela.zenid.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Flux<Profile>> findAll() {
        return ok(profileRepository.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Mono<Profile>> findByEmail(@PathVariable("email") @NonNull String email) {

        Profile profile = profileRepository.findById(email).block();
        if (profile == null) {
            return notFound().build();
        }

        return ok(Mono.just(profile));

    }

    @GetMapping(path = "/{firstname}/{lastname}", produces = "application/json")
    public ResponseEntity<Flux<Profile>> findByFirstAndLastName(@PathVariable("firstname") @NonNull String firstname,
                                                                @PathVariable("lastname") @NonNull String lastname) {

        List<Profile> profile = profileRepository.findByFirstNameAndLastName(firstname, lastname).collectList().block();
        if (profile == null) {
            return notFound().build();
        }

        if (profile.size() == 0) {
            return noContent().build();
        }

        Flux<Profile> profileFlux = Flux.fromArray(
                profile.toArray(new Profile[0])
        );

        return ok(profileFlux);

    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Mono<Profile>> save(@RequestBody @NonNull Profile profile) {
        return ok(profileRepository.save(profile));
    }

    @PatchMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Mono<Profile>> updateByEmail(@PathVariable("email") @NonNull String email,
                                                       @RequestBody @NonNull Profile body) {

        Profile profile = profileRepository.findById(email).block();
        if (profile == null) {
            return notFound().build();
        }

        Mono<Profile> update = profileRepository.save(body);

        return ok(update);
    }

    @DeleteMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Mono<Void>> deleteByEmail(@PathVariable("email") @NonNull String email) {

        Profile profile = profileRepository.findById(email).block();
        if (profile == null) {
            return notFound().build();
        }

        Mono<Void> delete = profileRepository.delete(profile);

        return ok(delete);

    }

}