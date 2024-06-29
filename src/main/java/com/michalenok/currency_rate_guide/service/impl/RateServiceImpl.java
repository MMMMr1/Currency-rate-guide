package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.service.CurrencyService;
import com.michalenok.currency_rate_guide.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final NbrbClient nbrbClient;
    private final CurrencyService currencyService;
    @Override
    public void saveRates(String periodicity, String ondate) {
        currencyService.saveCurrencies(periodicity);
        nbrbClient.getRates(periodicity, ondate);
    }
}
