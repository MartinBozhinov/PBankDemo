package com.example.demo.domain.dto;

import lombok.Getter;

@Getter
public class CurrencyDTO {
    private String currencyCode;
    private String currencyName;
    private double rate;

    public CurrencyDTO(String currencyCode, String currencyName, double rate) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
