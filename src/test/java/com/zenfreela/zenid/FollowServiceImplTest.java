package com.zenfreela.zenid;

import com.zenfreela.zenid.model.Follow;
import com.zenfreela.zenid.service.follow.FollowServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowServiceImplTest {

    @Autowired
    private FollowServiceImpl followService;

    @Test
    public void findAll() {
        Flux<Follow> followFlux = followService.findAll();
        List<Follow> followList = followFlux.collectList().block();

        assertThat(followList, notNullValue());
    }

    @Test
    public void register() {
        String follow1Email = "jailsondev70@gmail.com";
        String follow2Email = "tomasrsduarte@gmail.com";

        Follow follow = new Follow();
        follow.setEmail(follow1Email);
        follow.setFollower(follow2Email);

        Mono<Follow> followMono = followService.save(follow);
        Follow followSave = followMono.block();

        assertThat(followSave, notNullValue());
        assertThat(followSave.getEmail(), is(follow1Email));
        assertThat(followSave.getFollower(), is(follow2Email));
    }

    @Test
    public void findByEmail() {
        String email = "jailsondev70@gmail.com";

        Flux<Follow> followFlux = followService.findByEmail(email);
        List<Follow> followList = followFlux.collectList().block();

        assertThat(followList, notNullValue());
        followList.forEach(follow -> assertThat(follow.getEmail(), is(email)));
    }

    @Test
    public void delete() {
        String follow1Email = "jailsondev70@gmail.com";
        String follow2Email = "tomasrsduarte@gmail.com";

        Mono<Long> longMono = followService.deleteByEmailAndFollower(follow1Email, follow2Email);
        Long follow = longMono.block();

        assertThat(follow, notNullValue());
    }

}