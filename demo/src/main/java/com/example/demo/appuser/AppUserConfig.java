package com.example.demo.appuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppUserConfig {

    @Bean
    CommandLineRunner commandLineRunnerForAppUser(AppUserRepository repository){
        return args -> {
            AppUser adam = new AppUser( "Adam", "Admin", "adam@gmail.com");
            AppUser alex = new AppUser( "Alex", "User", "alex@gmail.com");

            repository.saveAll(List.of(adam,alex));
        };
    }
}
