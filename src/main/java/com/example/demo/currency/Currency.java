package com.example.demo.currency;

import com.example.demo.appuser.AppUser;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Currency {

    @Id
    @SequenceGenerator(
            name="coin_sequence",
            sequenceName = "coin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coin_sequence"
    )


    private Long id;
    private String name;
    private String symbol;
    private Double currentPrice;
    private LocalDateTime createdTime;
    private Boolean enabled;



    public Currency() {
    }

    public Currency(Long id, String name, String symbol, Double currentPrice, LocalDateTime createdTime, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.createdTime = createdTime;
        this.enabled = enabled;
    }

    public Currency(String name, String symbol, Double currentPrice, LocalDateTime createdTime, Boolean enabled) {
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.createdTime = createdTime;
        this.enabled = enabled;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                ", createdTime=" + createdTime +
                ", enabled=" + enabled +
                '}';
    }
}
