package com.example.demo.scheduler;

import com.example.demo.alert.Alert;
import com.example.demo.alert.AlertRepository;
import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.currency.Currency;
import com.example.demo.currency.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleTasks {

    private final AlertRepository alertRepository;
    private final CurrencyRepository currencyRepository;
    private final AppUserRepository appUserRepository;


    Logger logger = LoggerFactory.getLogger(ScheduleTasks.class);

    public ScheduleTasks(AlertRepository alertRepository, CurrencyRepository currencyRepository, AppUserRepository appUserRepository) {
        this.alertRepository = alertRepository;
        this.currencyRepository = currencyRepository;
        this.appUserRepository = appUserRepository;
    }

    //run scheduling job every 30 seconds
    @Scheduled(fixedRate = 30000)
    public void checkTask(){

        System.out.println("*********************** checkTask() call started *********************");

        List<Alert> alertList = alertRepository.findAll();

        for (Alert al : alertList){
            Double targetPrice = al.getTargetPrice();
            String currencyName = al.getCurrencyName();
            Long createdByUser = al.getCreatedByUser();

            Optional<Currency> currency = currencyRepository.findCurrencyByName(currencyName);
            boolean targetPriceReached = currency.isPresent() && currency.get().getCurrentPrice().equals(targetPrice) && al.getStatus().equals("TRIGGERED");
            if (targetPriceReached){

                Optional<AppUser> appUser = appUserRepository.findById(createdByUser);
                String userName = appUser.get().getName();
                String email = appUser.get().getEmail();

                System.out.println("Currency: ["+currencyName+"] has reached target price");
                System.out.println("--[" +al.getName()+ " is triggered now!");
                System.out.println("----User: " +userName+ " has been notified through mail: " +email);
            }
        }

        System.out.println("*********************** checkTask() call finished *********************");


    }
}
