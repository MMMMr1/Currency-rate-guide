package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.mapper.CurrencyMapper;
import com.michalenok.currency_rate_guide.repository.CurrencyRepository;
import com.michalenok.currency_rate_guide.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final NbrbClient nbrbClient;
    private final CurrencyMapper currencyMapper;
    private final CurrencyRepository currencyRepository;

    @Override
    public void saveCurrencies(String periodicity) {
        currencyRepository.saveAll(nbrbClient.getCurrencies(periodicity).stream().map(currencyMapper::currencyResponseToCurrency).toList());
    }
}
