package com.michalenok.currency_rate_guide.service;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;

import java.time.LocalDate;

/**
 * Service for managing data on rates
 */
public interface RateService {
    /**
     * method for obtaining exchange rate data from the
     * NBRB website and saving it at database
     * @param periodicity frequency of setting the rate (0 - daily, 1 - monthly)
     * @param ondate the date on which the rate is requested
     */
    void saveRates(String periodicity, String ondate);

    /**
     * method for getting exchange rate
     * @param curId
     * @param ondate the date on which the rate is requested
     * @param parammode cur_id argument format: 0 - internal currency code,
     *                  1 - three-digit numeric currency code according to ISO 4217 standard,
     *                  2 - three-digit alphabetic currency code (ISO 4217). Default = 0
     * @return @see RateResponse
     */
    RateResponse getRate(String curId, LocalDate ondate, int parammode);

}
