package com.example.demo.controller;

import com.example.demo.domain.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyController {

    private CurrencyService service;

    @GetMapping("/download-currencies")
    public String downloadCurrencies() {
        boolean updated = service.downloadAndSaveCurrencies();
        if (updated) {
            return "Currencies updated successfully.";
        } else {
            return "No new currencies updates found.";
        }
    }
}
