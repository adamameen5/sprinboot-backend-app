package com.example.demo.appuser;


import com.example.demo.alert.Alert;
import com.example.demo.currency.Currency;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class AppUser {

    @Id
    @SequenceGenerator(
            name="app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )


    private Long id;
    private String name;
    private String userType;
    private String email;

    //One user can have many alerts
    @OneToMany(targetEntity = Alert.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userAlert_fk",referencedColumnName = "id")
    private List<Alert> alerts;


    public AppUser() {
    }

    public AppUser(Long id, String name, String userType, String email) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.email = email;
    }

    public AppUser(String name, String userType, String email) {
        this.name = name;
        this.userType = userType;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
