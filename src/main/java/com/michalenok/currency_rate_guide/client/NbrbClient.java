package com.michalenok.currency_rate_guide.client;

import com.michalenok.currency_rate_guide.model.dto.CurrencyResponse;
import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Сlient for obtaining exchange rate data from the
 * NBRB website (API of the national bank: https://www.nbrb.by/apihelp/exrates).
 */
@FeignClient(name = "nbrbClient", url = "${nbrb.url}")
public interface NbrbClient {
    /**
     * method for obtaining the official exchange rate of the Belarusian ruble
     * against foreign currencies set by the National Bank on a specific date
     * @param periodicity frequency of setting the rate (0 - daily, 1 - monthly)
     * @param ondate date for which the rate is requested (if not set, the rate for today is returned)
     * @return list of rates @see RateResponse
     */
    @GetMapping(path = "/exrates/rates?{periodicity}&{ondate}")
    List<RateResponse> getRates(
            @RequestParam String periodicity,
            @RequestParam String ondate
    );

    /**
     * method for obtaining full list of foreign currencies against which the National Bank
     * sets the official exchange rate of the Belarusian ruble
     * @return list of foreign currencies @see CurrencyResponse
     */
    @GetMapping(path = "/exrates/currencies")
    List<CurrencyResponse> getCurrencies(
    );
}
