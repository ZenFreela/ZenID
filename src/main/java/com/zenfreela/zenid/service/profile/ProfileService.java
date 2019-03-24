package com.zenfreela.zenid.service.profile;

import com.zenfreela.zenid.model.Profile;
import com.zenfreela.zenid.service.CrudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProfileService extends CrudService<Profile> {

    Mono<Profile> findByEmail(String email);

    Flux<Profile> findByFirstNameAndLastName(String firstName, String lastName);

    Mono<Profile> updateByEmail(String email, Profile body);

    Mono<Void> deleteByEmail(String email);

}