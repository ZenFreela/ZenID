package com.zenfreela.zenid.repository;

import com.zenfreela.zenid.model.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {

    Flux<Profile> findByFirstNameAndLastName(String firstName, String lastName);

}