package com.example.demo.domain.currency.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name = "CurrencyList")
public class CurrencyList {

    @XmlElement(name = "Currency")
    private List<Currency> currencies;

    public List<Currency> getCurrencies() {
        return currencies;
    }
}