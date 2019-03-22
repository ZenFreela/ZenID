package com.zenfreela.zenid.repository;

import com.zenfreela.zenid.model.Follow;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FollowRepository extends ReactiveMongoRepository<Follow, String> {}