package com.example.demo.alert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Alert {

    @Id
    @SequenceGenerator(
            name="alert_sequence",
            sequenceName = "alert_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "alert_sequence"
    )

    private Long id;
    private String name;
    private String currencyName;

    private Double targetPrice;
    private LocalDateTime createdTime;
    private String status;
    private Long createdByUser;

    public Alert() {
    }

    public Alert(Long id, String name, String currencyName, Double targetPrice, LocalDateTime createdTime, String status, Long createdByUser) {
        this.id = id;
        this.name = name;
        this.currencyName = currencyName;
        this.targetPrice = targetPrice;
        this.createdTime = createdTime;
        this.status = status;
        this.createdByUser = createdByUser;
    }


    public Alert(String name, String currencyName, Double targetPrice, LocalDateTime createdTime, String status, Long createdByUser) {
        this.name = name;
        this.currencyName = currencyName;
        this.targetPrice = targetPrice;
        this.createdTime = createdTime;
        this.status = status;
        this.createdByUser = createdByUser;
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

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currency) {
        this.currencyName = currency;
    }

    public Double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(Long createdByUser) {
        this.createdByUser = createdByUser;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency='" + currencyName + '\'' +
                ", targetPrice=" + targetPrice +
                ", createdTime=" + createdTime +
                ", status='" + status + '\'' +
                ", createdByUser=" + createdByUser +
                '}';
    }
}
