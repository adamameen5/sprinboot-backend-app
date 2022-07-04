package com.example.demo.appuser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;
    private AppUserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AppUserService(appUserRepository);
    }



    @Test
    void canGetAllAppUsers() {
        //When
        underTest.getAppUsers();

        //Then
        verify(appUserRepository).findAll();
    }

    @Test
    @Disabled
    void canAddNewAppUser() {
        //Given
        AppUser registeredByUser= new AppUser("Halka","Admin","halka@gmail.com");

        AppUser appUser = new AppUser("Adam","Admin","adam@gmail.com");

        appUserRepository.save(registeredByUser);

        //When
        underTest.addNewAppUser(appUser, registeredByUser.getName());

        //Then
        ArgumentCaptor<AppUser> appUserArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);

        verify(appUserRepository).save(appUserArgumentCaptor.capture());

        AppUser capturedAppUser = appUserArgumentCaptor.getValue();

        assertThat(capturedAppUser).isEqualTo(appUser);

    }


    @Test
    @Disabled
    void willThrowWhenEmailIsTaken() {
        //Given
        AppUser appUser = new AppUser("Adam","Admin","adam@gmail.com");
        AppUser registeredByUser= new AppUser("Adam","Admin","adam@gmail.com");

        Optional<AppUser> appUserByEmail = appUserRepository.findAppUserByEmail(appUser.getEmail());
        boolean expected = appUserByEmail.isEmpty();

        given(expected).willReturn(true);

        //When
        //Then
        assertThatThrownBy(() -> underTest.addNewAppUser(appUser, registeredByUser.getName())).hasMessageContaining("Email already exists!");

    }

    @Test
    @Disabled
    void deleteAppUser() {
    }

    @Test
    @Disabled
    void updateAppUser() {
    }
}