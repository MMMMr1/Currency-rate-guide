package com.michalenok.currency_rate_guide.service;

import java.time.LocalDate;

/**
 * Service for managing data on currencies
 */
public interface CurrencyService {
    /**
     * method for obtaining data on currencies from the NBRB website
     * for a certain day and saving the obtained data in the database
     */
    void saveCurrencies();

    /**
     * method for searching curId (internal code)
     * @param curID currency code (numeric code)
     * @param ondate the date on which the rate is requested
     * @return curId
     */
    String findByCurrencyCode(String curID, LocalDate ondate);

    /**
     * method for searching curId (internal code)
     * @param curID currencyAbbreviation (letter code)
     * @param ondate the date on which the rate is requested
     * @return curId
     */
    String findByCurrencyAbbreviation(String curID, LocalDate ondate);
}
