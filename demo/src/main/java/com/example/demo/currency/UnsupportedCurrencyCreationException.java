package com.example.demo.currency;

public class UnsupportedCurrencyCreationException extends Exception{
    public UnsupportedCurrencyCreationException(String errorMessage) {
        super(errorMessage);
    }
}
