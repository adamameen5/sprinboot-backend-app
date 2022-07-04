package com.example.demo.alert;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class AlertConfig {

    @Bean
    CommandLineRunner commandLineRunnerForAlert(AlertRepository alertRepository){
        return args -> {
            Alert alert1 = new Alert("alert-1","coin-1", 287.2,LocalDateTime.now(),"NEW",1L);
            Alert alert2 = new Alert("alert-2","coin-2", 333.3,LocalDateTime.now(),"NEW",2L);

            alertRepository.saveAll(List.of(alert1,alert2));
        };
    }
}
