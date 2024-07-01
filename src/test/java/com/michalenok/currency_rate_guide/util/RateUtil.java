package com.michalenok.currency_rate_guide.util;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.model.entity.Rate;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class RateUtil {
    public Rate getRate() {
        return Rate.builder()
                .curId("451")
                .date(LocalDate.of(2024, 06, 30))
                .curOfficialRate("3.3821")
                .build();
    }

    public List<RateResponse> getRateResponses() {
        return List.of(
                RateResponse.builder()
                        .curId("451")
                        .date(LocalDate.of(2024, 06, 30))
                        .curOfficialRate("3.3821")
                        .build(),
                RateResponse.builder()
                        .curId("452")
                        .date(LocalDate.of(2024, 06, 30))
                        .curOfficialRate("3.3821")
                        .build(),
                RateResponse.builder()
                        .curId("453")
                        .date(LocalDate.of(2024, 06, 30))
                        .curOfficialRate("3.3821")
                        .build()
        );

    }

    public RateResponse getRateResponse() {
        return
                RateResponse.builder()
                        .curId("451")
                        .date(LocalDate.of(2024, 06, 30))
                        .curOfficialRate("3.3821")
                        .build();

    }
}
