package com.zenfreela.zenid.controller;

import com.zenfreela.zenid.model.Profile;
import com.zenfreela.zenid.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return ResponseEntity.ok(profileRepository.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Mono<Profile>> findByEmail(@PathVariable("email") String email) {
        if (email == null) {
            return ResponseEntity.badRequest().build();
        }

        Profile profile = profileRepository.findById(email).block();
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Mono.just(profile));
    }

}