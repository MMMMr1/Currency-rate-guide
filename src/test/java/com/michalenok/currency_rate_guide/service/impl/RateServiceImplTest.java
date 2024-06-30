package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.mapper.RateMapper;
import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.model.entity.Rate;
import com.michalenok.currency_rate_guide.model.entity.RateId;
import com.michalenok.currency_rate_guide.repository.RateRepository;
import com.michalenok.currency_rate_guide.service.CurrencyService;
import com.michalenok.currency_rate_guide.util.RateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.michalenok.currency_rate_guide.util.RateUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateServiceImplTest {
    @InjectMocks
    private RateServiceImpl rateService;
    @Mock
    private NbrbClient nbrbClient;
    @Mock
    private RateRepository rateRepository;
    @Mock
    private CurrencyService currencyService;
    @Mock
    private RateMapper rateMapper;

    @Test
    void saveRates_Successful() {
        List<RateResponse> rateResponses = getRateResponses();
        when(nbrbClient.getRates(any(String.class), any(String.class)))
                .thenReturn(rateResponses);

        rateService.saveRates("451", "2024-06-30");

        verify(rateRepository, Mockito.times(1)).saveAll(any(List.class));
        verify(nbrbClient, Mockito.times(1)).getRates(any(String.class), any(String.class));
        verify(rateMapper, Mockito.times(rateResponses.size())).rateResponseToRate(any(RateResponse.class));
    }

    @Test
    void getRateByCurId_Successful() {
        Rate rate = RateUtil.getRate();
        RateId rateId = new RateId("451", LocalDate.of(2024, 06, 28));

        when(rateRepository.findById(any(RateId.class)))
                .thenReturn(Optional.of(rate));
        when(rateMapper.rateToRateResponse(any(Rate.class)))
                .thenReturn(RateUtil.getRateResponse());

        RateResponse response = rateService.getRate("451", LocalDate.of(2024, 06, 30), 0);

        assertThat(response).isNotNull();
        assertThat(response.curId().equals(rate.getCurId()));
        assertThat(response.date().equals(rate.getDate()));
        assertThat(response.curOfficialRate().equals(rate.getCurOfficialRate()));
    }

    @Test
    void getRateByCurrencyCode_Successful() {
        Rate rate = RateUtil.getRate();
        String curId = "008";
        LocalDate ondate = LocalDate.of(2024, 06, 30);

        when(currencyService.findByCurrencyCode(curId, ondate))
                .thenReturn("451");
        when(rateRepository.findById(any(RateId.class)))
                .thenReturn(Optional.of(rate));
        when(rateMapper.rateToRateResponse(any(Rate.class)))
                .thenReturn(RateUtil.getRateResponse());

        RateResponse response = rateService.getRate(curId, ondate , 1);

        assertThat(response).isNotNull();
        assertThat(response.curId().equals(rate.getCurId()));
        assertThat(response.date().equals(rate.getDate()));
        assertThat(response.curOfficialRate().equals(rate.getCurOfficialRate()));
    }
    @Test
    void getRateByCurrencyAbbreviation_Successful() {
        Rate rate = RateUtil.getRate();
        String curId = "ALL";
        LocalDate ondate = LocalDate.of(2024, 06, 30);

        when(currencyService.findByCurrencyAbbreviation(curId, ondate))
                .thenReturn("451");
        when(rateRepository.findById(any(RateId.class)))
                .thenReturn(Optional.of(rate));
        when(rateMapper.rateToRateResponse(any(Rate.class)))
                .thenReturn(RateUtil.getRateResponse());

        RateResponse response = rateService.getRate(curId, ondate , 2);

        assertThat(response).isNotNull();
        assertThat(response.curId().equals(rate.getCurId()));
        assertThat(response.date().equals(rate.getDate()));
        assertThat(response.curOfficialRate().equals(rate.getCurOfficialRate()));
    }
}