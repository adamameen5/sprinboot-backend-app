package com.example.demo.useralert;

import com.example.demo.alert.AlertRepository;
import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAlertController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AlertRepository alertRepository;

    @PostMapping("/v1/api/createAlert")
    public AppUser createAlert(@RequestBody UserAlert userAlert){
       return appUserRepository.save(userAlert.getAppUser());
    }

    @GetMapping("/v1/api/findAllAlerts")
    public List<AppUser> findAllAlerts(){
        return appUserRepository.findAll();
    }
}
