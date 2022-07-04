package com.example.demo.appuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    @Disabled
    void itShouldFindUserByEmailExists() {

        //Given
        AppUser appUser = new AppUser("Adam","Admin","adam@gmail.com");

        underTest.save(appUser);

        //When
        String email = "adam@gmail.com";
        Optional<AppUser> appUserByEmail = underTest.findAppUserByEmail(email);
        boolean expected = appUserByEmail.isPresent();

        //Then
        assertThat(expected).isTrue();
    }


    @Test
    void itShouldFindIfUserByEmailDoesNotExist() {

        //Given
        String email = "adam@gmail.com";

        //When
        Optional<AppUser> appUserByEmail = underTest.findAppUserByEmail(email);
        boolean expected = appUserByEmail.isPresent();

        //Then
        assertThat(expected).isFalse();
    }

    @Test
    @Disabled
    void itShouldFindUserByName() {
    }
}