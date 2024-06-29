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

import java.util.Date;
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
        currencyService.saveCurrencies(periodicity);
        rateRepository.saveAll(nbrbClient.getRates(periodicity, ondate).stream()
                .map(rateMapper::rateResponseToRate)
                .toList());
    }

    @Override
    public RateResponse getRate(String curID, Date ondate, int parammode) {
        curID = getCurID(curID, ondate, parammode);
        return rateRepository.findById(new RateId(curID, ondate))
                .map(rateMapper::rateToRateResponse)
                .orElseThrow(NoSuchElementException::new);
    }

    private String getCurID(String curID, Date ondate, int parammode) {
        if (parammode == 1) {
            curID =  currencyService.findByCurrencyCode(curID, ondate);
        } else if (parammode == 2) {
            curID =  currencyService.findByCurrencyAbbreviation(curID, ondate);
        }
        return curID;
    }
}
