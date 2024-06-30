package com.michalenok.currency_rate_guide.client;

import com.michalenok.currency_rate_guide.model.dto.CurrencyResponse;
import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "nbrbClient", url = "${nbrb.url}")
public interface NbrbClient {
    @GetMapping(path = "/exrates/rates?{periodicity}&{ondate}")
    List<RateResponse> getRates(
            @RequestParam String periodicity,
            @RequestParam String ondate
    );
    @GetMapping(path = "/exrates/currencies")
    List<CurrencyResponse> getCurrencies(
    );
}
