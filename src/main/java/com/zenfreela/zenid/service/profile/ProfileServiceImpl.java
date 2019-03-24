package com.zenfreela.zenid.service.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenfreela.zenid.model.Profile;
import com.zenfreela.zenid.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository,
                              MongoTemplate mongoTemplate) {
        this.profileRepository = profileRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Profile> findByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public Flux<Profile> findByFirstNameAndLastName(String firstName, String lastName) {
        return profileRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Mono<Profile> updateByEmail(String email, Profile body) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> objectMap = objectMapper.convertValue(body, Map.class);
        objectMap.values().removeIf(Objects::isNull);

        Update update = new Update();
        objectMap.forEach(update::set);

        mongoTemplate.findAndModify(
                Query.query(Criteria.where("email").is(body.getEmail())),
                update,
                Profile.class
        );

        return Mono.just(body);
    }

    @Override
    public Mono<Void> deleteByEmail(String email) {
        return profileRepository.deleteByEmail(email);
    }

    @Override
    public Flux<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Mono<Profile> findById(String id) {
        return profileRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String... ids) {
        String id = ids[0];
        return profileRepository.deleteById(id);
    }

    @Override
    public Mono<Profile> save(Profile body) {
        return profileRepository.save(body);
    }

}