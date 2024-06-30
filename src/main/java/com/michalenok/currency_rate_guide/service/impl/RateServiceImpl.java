package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.mapper.RateMapper;
import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.model.entity.RateId;
import com.michalenok.currency_rate_guide.repository.RateRepository;
import com.michalenok.currency_rate_guide.service.CurrencyService;
import com.michalenok.currency_rate_guide.service.RateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

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
        currencyService.saveCurrencies();
        rateRepository.saveAll(nbrbClient.getRates(periodicity, ondate).stream()
                .map(rateMapper::rateResponseToRate)
                .toList());
    }

    @Override
    public RateResponse getRate(String curId, LocalDate ondate, int parammode) {
        curId = getCurId(curId, ondate, parammode);
        RateId rateId = new RateId(curId, ondate);
        return rateRepository.findById(rateId)
                .map(rateMapper::rateToRateResponse)
                .orElseThrow(() -> new NoSuchElementException("Failed to find rate"));
    }

    private String getCurId(String curId, LocalDate ondate, int parammode) {
        if (parammode == 1) {
            curId = currencyService.findByCurrencyCode(curId, ondate);
        } else if (parammode == 2) {
            curId = currencyService.findByCurrencyAbbreviation(curId, ondate);
        }
        return curId;
    }
}
