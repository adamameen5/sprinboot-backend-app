package com.example.demo.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/currency")
public class CurrencyController {

    private final CurrencyService currencyService;


    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    //API end point method to get list of all currencies
    @GetMapping
    public List<Currency> getListOfCurrencies(){
        return currencyService.getListOfCurrencies();
    }


    //API end point method to create a new currency
    @PostMapping
    public void createNewCurrency(@RequestBody Currency newCurrency, @RequestParam(required = true) String createdByUser) throws UnsupportedCurrencyCreationException {
        currencyService.createNewCurrency(newCurrency,createdByUser);
    }


    //API end point method to delete a currency
    @DeleteMapping(path ="{currencyName}")
    public void removeCurrency(@PathVariable("currencyName") String currencyName, @RequestParam(required = true) String removedByUser){
        currencyService.removeCurrency(currencyName,removedByUser);
    }


    //API end point method to update a currency
    @PutMapping(path="{currencyName}")
    public void updateCurrency(
            @PathVariable("currencyName") String currencyName,
            @RequestParam(required = false) String newName,
            @RequestParam(required = false) String newSymbol,
            @RequestParam(required = false) Double newPrice,
            @RequestParam(required = false) String newEnabledStatus,
            @RequestParam(required = true) String updatedByUser) throws UnsupportedCurrencyCreationException {
        currencyService.updateCurrency(currencyName,newName,newSymbol,newPrice,newEnabledStatus,updatedByUser);
    }



}
