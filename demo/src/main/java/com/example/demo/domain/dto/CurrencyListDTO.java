package com.example.demo.domain.dto;

import java.util.List;

public class CurrencyListDTO {

    private List<CurrencyDTO> currencies;

    public List<CurrencyDTO> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDTO> currencies) {
        this.currencies = currencies;
    }
}
