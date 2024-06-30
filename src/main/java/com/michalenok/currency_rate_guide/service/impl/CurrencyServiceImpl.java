package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.mapper.CurrencyMapper;
import com.michalenok.currency_rate_guide.model.entity.Currency;
import com.michalenok.currency_rate_guide.repository.CurrencyRepository;
import com.michalenok.currency_rate_guide.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final NbrbClient nbrbClient;
    private final CurrencyMapper currencyMapper;
    private final CurrencyRepository currencyRepository;

    @Override
    public void saveCurrencies() {
        currencyRepository.saveAll(nbrbClient.getCurrencies().stream()
                .map(currencyMapper::currencyResponseToCurrency)
                .toList());
    }

    @Override
    public String findByCurrencyCode(String curCode, LocalDate ondate) {
        return currencyRepository.findByCurCode(curCode, ondate)
                .map(Currency::getCurId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Failed to find curId by %s and %s", curCode, ondate)
                ));
    }

    @Override
    public String findByCurrencyAbbreviation(String curAbbreviation, LocalDate ondate) {
        return currencyRepository.findByCurAbbreviation(curAbbreviation, ondate)
                .map(Currency::getCurId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Failed to find curId by %s and %s", curAbbreviation, ondate)
                ));
    }
}
