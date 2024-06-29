package com.michalenok.currency_rate_guide.service;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;

import java.util.Date;

public interface RateService {
    void saveRates(String periodicity, String ondate);

    RateResponse getRate(String curId, Date ondate, int parammode);

}
