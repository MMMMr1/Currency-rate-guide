package com.michalenok.currency_rate_guide.service;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;

import java.time.LocalDate;

public interface RateService {
    void saveRates(String periodicity, String ondate);

    RateResponse getRate(String curId, LocalDate ondate, int parammode);

}
