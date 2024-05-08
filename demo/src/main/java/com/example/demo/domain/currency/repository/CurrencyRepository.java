package com.example.demo.domain.currency.repository;

import com.example.demo.domain.currency.entity.Currency;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    @Nonnull
    Optional<Currency> findById(@Nonnull Integer id);

    @Nonnull
    List<Currency> findAll();
}
