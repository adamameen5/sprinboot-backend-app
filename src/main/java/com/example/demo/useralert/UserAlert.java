package com.example.demo.useralert;

import com.example.demo.appuser.AppUser;

import javax.persistence.*;


public class UserAlert {


    private AppUser appUser;

    public UserAlert() {
    }

    public UserAlert(Long id, AppUser appUser) {
        this.appUser = appUser;
    }

    public UserAlert(AppUser appUser) {
        this.appUser = appUser;
    }



    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
