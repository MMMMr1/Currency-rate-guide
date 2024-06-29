package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.mapper.RateMapper;
import com.michalenok.currency_rate_guide.repository.RateRepository;
import com.michalenok.currency_rate_guide.service.CurrencyService;
import com.michalenok.currency_rate_guide.service.RateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final NbrbClient nbrbClient;
    private final CurrencyService currencyService;
    private final RateRepository rateRepository;
    private final RateMapper rateMapper;
    @Override
    @Transactional
    public void saveRates(String periodicity, String ondate) {
        currencyService.saveCurrencies(periodicity);
        rateRepository.saveAll(nbrbClient.getRates(periodicity, ondate).stream().map(rateMapper::rateResponseToRate).toList());
    }
}
