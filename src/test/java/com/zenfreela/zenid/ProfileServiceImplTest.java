package com.zenfreela.zenid;

import com.zenfreela.zenid.model.Profile;
import com.zenfreela.zenid.service.profile.ProfileServiceImpl;
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
public class ProfileServiceImplTest {

    @Autowired
    private ProfileServiceImpl profileService;

    @Test
    public void findAll() {
        Flux<Profile> profileFlux = profileService.findAll();
        List<Profile> profileList = profileFlux.collectList().block();

        assertThat(profileList, notNullValue());
    }

    @Test
    public void register() {
        Profile profile = new Profile();
        profile.setEmail("jailsondev70@gmail.com");
        profile.setFirstName("Jailson");
        profile.setLastName("Pereira");
        profile.setCpf("13278325422");
        profile.setBiography("Lorem ipsum.");
        profile.setImage("default.jpg");

        Mono<Profile> profileMono = profileService.save(profile);
        Profile profileSave = profileMono.block();

        assertThat(profileSave, notNullValue());
        assertThat(profile.getEmail(), is(profileSave.getEmail()));
    }

    @Test(expected = AssertionError.class)
    public void updateByEmail() {
        String email = "jailsondev70@gmail.com";
        String biography = "ZenFreela is love.";

        Mono<Profile> profileMono = profileService.findByEmail(email);
        Profile profile = profileMono.block();

        assertThat(profile, notNullValue());

        profile.setBiography(biography);

        Mono<Profile> updatedMono = profileService.updateByEmail(email, profile);
        Profile updated = updatedMono.block();

        assertThat(updated, notNullValue());
        assertThat(updated.getEmail(), is(email));
        assertThat(updated.getBiography(), is(biography));
    }

    @Test
    public void findByEmail() {
        String email = "jailsondev70@gmail.com";

        Mono<Profile> profileMono = profileService.findByEmail(email);
        Profile profile = profileMono.block();

        assertThat(profile, notNullValue());
    }

    @Test
    public void delete() {
        String email = "jailsondev70@gmail.com";

        Mono<Long> longMono = profileService.deleteByEmail(email);
        Long profile = longMono.block();

        assertThat(profile, notNullValue());
    }

}