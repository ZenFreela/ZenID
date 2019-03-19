package com.zenfreela.zenid.repository;

import com.zenfreela.zenid.model.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {}