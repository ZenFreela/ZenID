package com.zenfreela.zenid.repository;

import com.zenfreela.zenid.model.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {

    Mono<Profile> findByEmail(String email);

    Flux<Profile> findByFirstNameAndLastName(String firstName, String lastName);

    Mono<Long> deleteByEmail(String email);

}