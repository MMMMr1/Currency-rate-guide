package com.michalenok.currency_rate_guide.util;

import com.michalenok.currency_rate_guide.model.dto.CurrencyResponse;
import com.michalenok.currency_rate_guide.model.entity.Currency;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class CurrencyUtil {
    public List<CurrencyResponse> getCurrencyResponses() {
        return List.of(
                new CurrencyResponse("123", "123", "USD", "Доллар", LocalDate.of(2000, 10, 10), LocalDate.of(2025, 10, 10)),
                new CurrencyResponse("124", "323", "USS", "Доллар", LocalDate.of(2000, 10, 10), LocalDate.of(2025, 10, 10)),
                new CurrencyResponse("125", "423", "USF", "Доллар", LocalDate.of(2000, 10, 10), LocalDate.of(2025, 10, 10)),
                new CurrencyResponse("126", "523", "USV", "Доллар", LocalDate.of(2000, 10, 10), LocalDate.of(2025, 10, 10))
        );
    }

    public Currency getCurrency() {
        Currency currency = Currency.builder()
                .curId("1")
                .curCode("008")
                .curAbbreviation("ALL")
                .curName("Албанский лек")
                .curDateStart(LocalDate.of(2000, 10, 10))
                .curDateEnd(LocalDate.of(2025, 10, 10))
                .build();
        return currency;
    }
}
