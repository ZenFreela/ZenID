package com.zenfreela.zenid.service.follow;

import com.zenfreela.zenid.model.Follow;
import com.zenfreela.zenid.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FollowServiceImpl implements FollowService {

    private FollowRepository followRepository;

    @Autowired
    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public Flux<Follow> findByEmail(String email) {
        return followRepository.findByEmail(email);
    }

    @Override
    public Mono<Follow> findByEmailAndFollower(String email, String follower) {
        return followRepository.findByEmailAndFollower(email, follower);
    }

    @Override
    public Mono<Long> deleteByEmailAndFollower(String email, String follower) {
        return followRepository.deleteByEmailAndFollower(email, follower);
    }

    @Override
    public Flux<Follow> findAll() {
        return followRepository.findAll();
    }

    @Override
    public Mono<Follow> findById(String id) {
        return followRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return followRepository.deleteById(id);
    }

    @Override
    public Mono<Follow> save(Follow body) {
        return followRepository.save(body);
    }

}