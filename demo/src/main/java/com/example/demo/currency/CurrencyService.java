package com.example.demo.currency;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final AppUserRepository appUserRepository;


    private enum UnSupportedTypes {
        ETH,
        LTC,
        ZKN,
        MRD,
        LPR,
        GBZ
    }


    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, AppUserRepository appUserRepository) {
        this.currencyRepository = currencyRepository;
        this.appUserRepository = appUserRepository;
    }


    public List<Currency> getListOfCurrencies(){
        return currencyRepository.findAll();
    }


    public void createNewCurrency(Currency newCurrency, String createdByUser) throws UnsupportedCurrencyCreationException {

        Optional<AppUser> userDetails = appUserRepository.findAppUserByName(createdByUser);

        if(userDetails.isEmpty()){
            throw new IllegalStateException("Sorry, the user sending the request is not registered.");
        }

        validateUser(userDetails);

        Optional<Currency> currencyByName = currencyRepository.findCurrencyByName(newCurrency.getName());
        if (currencyByName.isPresent()){
            throw new IllegalStateException("There is already a currency by the name of '"+newCurrency.getName());
        }

        String newCurrencyType = newCurrency.getSymbol();
        for(UnSupportedTypes us : UnSupportedTypes.values()){
            if(newCurrencyType.equals(us.name())){
                throw new UnsupportedCurrencyCreationException("Sorry this currency type is not accepted!");
            }
        }
        newCurrency.setCreatedTime(LocalDateTime.now());
        currencyRepository.save(newCurrency);
    }


    public void removeCurrency(String currencyName, String removedByUser) {

        Optional<AppUser> userDetails = appUserRepository.findAppUserByName(removedByUser);
        if(userDetails.isEmpty()){
            throw new IllegalStateException("Sorry, the user sending the request is not registered.");
        }
        validateUser(userDetails);

        Optional<Currency> currencyByName = currencyRepository.findCurrencyByName(currencyName);

        if (!currencyByName.isPresent()){
            throw new IllegalStateException("Currency with the name of: '"+currencyName+"' could not be found!");
        }
        Currency selectedCurrency = currencyByName.get();
        currencyRepository.deleteById(selectedCurrency.getId());
    }


    @Transactional
    public void updateCurrency(String currencyName, String newName, String newSymbol, Double newPrice, String newEnabledStatus, String updatedByUser) throws UnsupportedCurrencyCreationException {

        Optional<AppUser> userDetails = appUserRepository.findAppUserByName(updatedByUser);
        if(userDetails.isEmpty()){
            throw new IllegalStateException("Sorry, the user sending the request is not registered.");
        }
        validateUser(userDetails);

        Currency existingCurrency = currencyRepository.findCurrencyByName(currencyName).orElseThrow( () -> new IllegalStateException("Currency with the name of: '"+currencyName+"' could not be found!"));


        if (newName != null && newName.length()>0 && !Objects.equals(existingCurrency.getName(),newName)){
            existingCurrency.setName(newName);
        }

        if (newSymbol != null && !Objects.equals(existingCurrency.getSymbol(),newSymbol)){
            for(UnSupportedTypes us : UnSupportedTypes.values()) {
                if (newSymbol.equals(us.name())) {
                    throw new UnsupportedCurrencyCreationException("Sorry this currency type is not accepted!");
                }
            }
            existingCurrency.setSymbol(newSymbol);
        }

        if (newPrice != null && !Objects.equals(existingCurrency.getCurrentPrice(),newPrice)){
            existingCurrency.setCurrentPrice(newPrice);
        }

        if (newEnabledStatus != null && !Objects.equals(existingCurrency.getEnabled(),newEnabledStatus)){
            existingCurrency.setEnabled(Boolean.parseBoolean(newEnabledStatus));
        }
    }

    private void validateUser(Optional<AppUser> userDetails) {
        AppUser appUser = userDetails.get();

        String userType= appUser.getUserType();

        if(!userType.equals("Admin")){
            throw new IllegalStateException("Sorry only Admins can manage currency types.");
        }
    }
}
