package com.example.demo.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/user")
public class AppUserController {


    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getUsers(){
        return appUserService.getAppUsers();
    }

    @PostMapping
    public void registerNewAppUser(@RequestBody AppUser appUser, @RequestParam(required = true) String byUser){
        appUserService.addNewAppUser(appUser, byUser);
    }

    @DeleteMapping(path ="{appUserID}")
    public void deleteAppUser(@PathVariable("appUserID") Long appUserID,  @RequestParam(required = true) String byUser){
        appUserService.deleteAppUser(appUserID, byUser);
    }


    @PutMapping(path="{appUserID}")
    public void updateAppUser(
            @PathVariable("appUserID") Long appUserID,
            @RequestParam(required = false) String newName,
            @RequestParam(required = false) String newEmail,
            @RequestParam(required = false) String newUserType,
            @RequestParam(required = true) String byUser){
        appUserService.updateAppUser(appUserID,newName,newEmail,newUserType,byUser);
    }
}
