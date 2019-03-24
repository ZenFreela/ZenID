package com.zenfreela.zenid.service;

import com.zenfreela.zenid.model.AbstractModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService<T extends AbstractModel> {

    Flux<T> findAll();

    Mono<T> findById(String id);

    Mono<Void> delete(String... ids);

    Mono<T> save(T body);

}