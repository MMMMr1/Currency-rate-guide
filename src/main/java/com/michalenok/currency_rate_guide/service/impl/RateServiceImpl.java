package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final NbrbClient nbrbClient;
    @Override
    public void saveRates(String periodicity, String ondate) {
        List<RateResponse> rates = nbrbClient.getRates(periodicity, ondate);
    }
}
