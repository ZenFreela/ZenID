package com.zenfreela.zenid.repository;

import com.zenfreela.zenid.model.Follow;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FollowRepository extends ReactiveMongoRepository<Follow, String> {

    Flux<Follow> findByEmail(String email);

    Mono<Follow> findByEmailAndFollower(String email, String follower);

}