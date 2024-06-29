package com.michalenok.currency_rate_guide.service;

import java.util.Date;

public interface CurrencyService {
    void saveCurrencies(String periodicity);

    String findByCurrencyCode(String curID, Date ondate);

    String findByCurrencyAbbreviation(String curID, Date ondate);
}
