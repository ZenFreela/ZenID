package com.zenfreela.zenid.controller;

import com.zenfreela.zenid.model.Profile;
import com.zenfreela.zenid.service.profile.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileServiceImpl profileService;

    @Autowired
    public ProfileController(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Flux<Profile>> findAll() {
        return ok(profileService.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Mono<Profile>> findByEmail(@PathVariable("email") @NonNull String email) {
        return ok(profileService.findByEmail(email));
    }

    @GetMapping(path = "/{firstname}/{lastname}", produces = "application/json")
    public ResponseEntity<Flux<Profile>> findByFirstAndLastName(@PathVariable("firstname") @NonNull String firstname,
                                                                @PathVariable("lastname") @NonNull String lastname) {
        return ok(profileService.findByFirstNameAndLastName(firstname, lastname));

    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Mono<Profile>> save(@RequestBody @NonNull Profile body) {
        return ok(profileService.save(body));
    }

    @PatchMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Mono<Profile>> updateByEmail(@PathVariable("email") @NonNull String email,
                                                       @RequestBody @NonNull Profile body) {
        return ok(profileService.updateByEmail(email, body));
    }

    @DeleteMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Mono<Long>> deleteByEmail(@PathVariable("email") @NonNull String email) {
        return ok(profileService.deleteByEmail(email));
    }

}