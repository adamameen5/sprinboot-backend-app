package com.example.demo.currency;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class CurrencyConfig {

    @Bean
    CommandLineRunner commandLineRunnerForCurrency(CurrencyRepository currencyRepository){
        return args -> {
            Currency coin1 = new Currency("coin-1","ADM",109.1, LocalDateTime.now(),true);
            Currency coin2 = new Currency("coin-2","LUX",221.96, LocalDateTime.now(),true);

            currencyRepository.saveAll(List.of(coin1,coin2));
        };
    }
}
