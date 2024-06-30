package com.michalenok.currency_rate_guide.service;

import java.time.LocalDate;

public interface CurrencyService {
    void saveCurrencies();

    String findByCurrencyCode(String curID, LocalDate ondate);

    String findByCurrencyAbbreviation(String curID, LocalDate ondate);
}
