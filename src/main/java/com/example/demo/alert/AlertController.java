package com.example.demo.alert;

import com.example.demo.currency.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/alert")
public class AlertController {

    private final AlertService alertService;


    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }


    @GetMapping
    public List<Alert> getListOfAlerts(){
        return alertService.getListOfAlerts();
    }


    @PostMapping
    public void createNewAlert(@RequestBody Alert newAlert, @RequestParam(required = true) String createdByUser){
        alertService.createNewAlert(newAlert,createdByUser);
    }


    @PutMapping(path="{alertName}")
    public void updateAlert(
            @PathVariable("alertName") String alertName,
            @RequestParam(required = false) String newName,
            @RequestParam(required = false) String newCurrency,
            @RequestParam(required = false) Double newTargetPrice){
        alertService.updateAlert(alertName,newName,newCurrency,newTargetPrice);
    }


    @DeleteMapping(path="{alertName}")
    public void deleteAlert(@PathVariable("alertName") String alertName){
        alertService.deleteAlert(alertName);
    }


    @PutMapping(path="close-alert/{alertName}")
    public void closeAlert(
            @PathVariable("alertName") String alertName){
        alertService.closeAlert(alertName);
    }


    @PutMapping(path="cancel-alert/{alertName}")
    public void cancelAlert(
            @PathVariable("alertName") String alertName){
        alertService.cancelAlert(alertName);
    }
}
