package com.example.demo.alert;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.currency.Currency;
import com.example.demo.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final AppUserRepository appUserRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public AlertService(AlertRepository alertRepository, AppUserRepository appUserRepository, CurrencyRepository currencyRepository) {
        this.alertRepository = alertRepository;
        this.appUserRepository = appUserRepository;
        this.currencyRepository = currencyRepository;
    }


    public List<Alert> getListOfAlerts() {
        return alertRepository.findAll();
    }


    @Transactional
    public void createNewAlert(Alert newAlert, String createdByUser) {

        Optional<AppUser> user = appUserRepository.findAppUserByName(createdByUser);

        if (!user.isPresent()){
            throw new IllegalStateException("This User is not registered to create an alert.");
        }

        String alertStatus = getAlertStatus(newAlert);
        newAlert.setStatus(alertStatus);

        newAlert.setCreatedByUser(user.get().getId());

        Optional<Alert> alertByName = alertRepository.findAlertByName(newAlert.getName());
        if (alertByName.isPresent()){
            throw new IllegalStateException("There is already an alert with the name of '"+newAlert.getName()+"'");
        }
        newAlert.setCreatedTime(LocalDateTime.now());
        alertRepository.save(newAlert);
    }

    private String getAlertStatus(Alert newAlert) {
        Optional<Currency> currency = currencyRepository.findCurrencyByName(newAlert.getCurrencyName());
        if (!currency.isPresent()){
            throw new IllegalStateException("Please provide a currency for the alert.");
        }

        String status = "";

        Double currentPriceOfSelectedCurrency = currency.get().getCurrentPrice();
        Double targetPrice = newAlert.getTargetPrice();

        if (!currentPriceOfSelectedCurrency.equals(targetPrice)){
            status = "NEW";
        } else if (currentPriceOfSelectedCurrency.equals(targetPrice)) {
            status = "TRIGGERED";
        }

        return status;
    }


    @Transactional
    public void updateAlert(String alertName, String newName, String newCurrency, Double newTargetPrice) {

        Alert existingAlert = alertRepository.findAlertByName(alertName).orElseThrow(() -> new IllegalStateException("Alert with the name of '"+alertName+"' could not be found."));

        if (newName != null && newName.length()>0 && !Objects.equals(existingAlert.getName(),newName)){
            existingAlert.setName(newName);
        }

        if (newCurrency != null && !Objects.equals(existingAlert.getCurrencyName(),newCurrency)){
            existingAlert.setCurrencyName(newCurrency);
        }

        if (newTargetPrice != null && newTargetPrice>0.0 && !Objects.equals(existingAlert.getTargetPrice(),newTargetPrice)){
            existingAlert.setTargetPrice(newTargetPrice);
        }

        String alertStatus = getAlertStatus(existingAlert);
        existingAlert.setStatus(alertStatus);

    }


    public void deleteAlert(String alertName) {
        Optional<Alert> existingAlert = alertRepository.findAlertByName(alertName);

        if (!existingAlert.isPresent()) {
            throw new IllegalStateException("Alert with the name of '"+alertName+"' could not be found.");
        }

        Alert selectedAlert = existingAlert.get();
        alertRepository.deleteById(selectedAlert.getId());
    }


    @Transactional
    public void closeAlert(String alertName) {
        Alert existingAlert = alertRepository.findAlertByName(alertName).orElseThrow(() -> new IllegalStateException("Alert with the name of '"+alertName+"' could not be found."));

        String alertStatus = getAlertStatus(existingAlert);
        if(alertStatus.equals("TRIGGERED")){
            existingAlert.setStatus("ACKED");
        }

    }


    @Transactional
    public void cancelAlert(String alertName) {
        Alert existingAlert = alertRepository.findAlertByName(alertName).orElseThrow(() -> new IllegalStateException("Alert with the name of '"+alertName+"' could not be found."));

        String alertStatus = getAlertStatus(existingAlert);
        if(!alertStatus.equals("TRIGGERED")){
            existingAlert.setStatus("CANCELLED");
        } else {
            throw new IllegalStateException("Sorry you can not cancel this alert since it's being triggered now.");
        }
    }
}
