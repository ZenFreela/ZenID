package com.zenfreela.zenid.controller;

import com.zenfreela.zenid.model.Follow;
import com.zenfreela.zenid.service.follow.FollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private FollowServiceImpl followService;

    @Autowired
    public FollowController(FollowServiceImpl followService) {
        this.followService = followService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Flux<Follow>> findAll() {
        return ok(followService.findAll());
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ResponseEntity<Flux<Follow>> findByEmail(@PathVariable("email") @NonNull String email) {
        return ok(followService.findByEmail(email));
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Mono<Follow>> save(@RequestBody @NonNull Follow body) {
        return ok(followService.save(body));
    }

    @DeleteMapping(path = "/{email}/{target}", produces = "application/json")
    public ResponseEntity<Mono<Void>> delete(@PathVariable("email") @NonNull String email,
                                             @PathVariable("target") @NonNull String target) {
        return ok(followService.delete(email, target));
    }

}