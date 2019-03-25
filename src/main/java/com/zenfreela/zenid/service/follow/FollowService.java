package com.zenfreela.zenid.service.follow;

import com.zenfreela.zenid.model.Follow;
import com.zenfreela.zenid.service.CrudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FollowService extends CrudService<Follow> {

    Flux<Follow> findByEmail(String email);

    Mono<Follow> findByEmailAndFollower(String email, String follower);

    Mono<Long> deleteByEmailAndFollower(String email, String follower);

}