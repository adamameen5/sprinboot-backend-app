package com.example.demo.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAppUsers(){
        return appUserRepository.findAll();
    }

    public void addNewAppUser(AppUser appUser, String registeredByUser) {
        Optional<AppUser> appUserByEmail = appUserRepository.findAppUserByEmail(appUser.getEmail());

        Optional<AppUser> userDetails = appUserRepository.findAppUserByName(registeredByUser);

        if(userDetails.isEmpty()){
            throw new IllegalStateException("Sorry, the user sending the request is not registered.");
        }

        if (appUserByEmail.isPresent()){
            throw new IllegalStateException("Email already exists!");
        }
        appUserRepository.save(appUser);
    }

    public void deleteAppUser(Long appUserID, String byUser) {
        Optional<AppUser> userDetails = appUserRepository.findAppUserByName(byUser);
        if(userDetails.isEmpty()){
            throw new IllegalStateException("Sorry, the user sending the request is not registered.");
        }

        boolean appUserExists = appUserRepository.existsById(appUserID);
        if(!appUserExists){
            throw new IllegalStateException("App user with ID: "+appUserID + " does not exist!");
        }

        appUserRepository.deleteById(appUserID);
    }

    @Transactional
    public void updateAppUser(Long appUserID, String newName, String newEmail, String newUserType, String byUser) {
        Optional<AppUser> userDetails = appUserRepository.findAppUserByName(byUser);
        if(userDetails.isEmpty()){
            throw new IllegalStateException("Sorry, the user sending the request is not registered.");
        }

        AppUser appUser = appUserRepository.findById(appUserID).orElseThrow( () -> new IllegalStateException("App User with ID: "+appUserID+" could not be found!"));

        if (newName != null && newName.length()>0 && !Objects.equals(appUser.getName(),newName)){
            appUser.setName(newName);
        }

        if (newEmail != null && !Objects.equals(appUser.getEmail(),newEmail)){
            Optional<AppUser> appUserOptional = appUserRepository.findAppUserByEmail(newEmail);

            if (appUserOptional.isPresent()){
                throw new IllegalStateException("App user with email: '"+newEmail+"' already exists.");
            }

            appUser.setEmail(newEmail);
        }

    }
}
