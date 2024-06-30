package com.michalenok.currency_rate_guide.service.impl;

import com.michalenok.currency_rate_guide.client.NbrbClient;
import com.michalenok.currency_rate_guide.mapper.CurrencyMapper;
import com.michalenok.currency_rate_guide.model.dto.CurrencyResponse;
import com.michalenok.currency_rate_guide.model.entity.Currency;
import com.michalenok.currency_rate_guide.repository.CurrencyRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.michalenok.currency_rate_guide.util.CurrencyUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {
    @InjectMocks
    private CurrencyServiceImpl currencyService;
    @Mock
    private NbrbClient nbrbClient;
    @Mock
    private CurrencyMapper currencyMapper;
    @Mock
    private CurrencyRepository currencyRepository;

    @SneakyThrows
    @Test
    void saveCurrencies() {
        List<CurrencyResponse> currencyResponses = getCurrencyResponses();

        when(nbrbClient.getCurrencies()).thenReturn(currencyResponses);

        currencyService.saveCurrencies();

        verify(currencyRepository, times(1)).saveAll(any(List.class));
        verify(nbrbClient, times(1)).getCurrencies();
        verify(currencyMapper, times(currencyResponses.size())).currencyResponseToCurrency(any(CurrencyResponse.class));
    }


    @SneakyThrows
    @Test
    void findByCurrencyCode_Successful() {
        Currency currency = getCurrency();

        when(currencyRepository.findByCurCode(any(String.class), any(LocalDate.class)))
                .thenReturn(Optional.of(currency));

        String byCurrencyCode = currencyService.findByCurrencyCode("008", LocalDate.of(2024, 06, 28));

        assertThat(byCurrencyCode)
                .isNotNull()
                .isEqualTo(currency.getCurId());
    }

    @SneakyThrows
    @Test
    void findByCurrencyCode_NoSuchElementException() {
        when(currencyRepository.findByCurCode(any(String.class), any(LocalDate.class)))
                .thenThrow(new NoSuchElementException(
                        String.format("Failed to find curId by %s and %s", "008", LocalDate.of(2024, 06, 28))
                ));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                currencyService.findByCurrencyCode("008", LocalDate.of(2024, 06, 28)));
        assertThat(exception)
                .hasMessageContaining("Failed to find curId by 008 and 2024-06-28");
    }

    @SneakyThrows
    @Test
    void findByCurrencyAbbreviation_Successful() {
        Currency currency = getCurrency();

        when(currencyRepository.findByCurAbbreviation(any(String.class), any(LocalDate.class)))
                .thenReturn(Optional.of(currency));

        String byCurrencyCode = currencyService.findByCurrencyAbbreviation("ALL", LocalDate.of(2024, 06, 28));

        assertThat(byCurrencyCode)
                .isNotNull()
                .isEqualTo(currency.getCurId());
    }

    @SneakyThrows
    @Test
    void findByCurrencyAbbreviation_NoSuchElementException() {
        when(currencyRepository.findByCurAbbreviation(any(String.class), any(LocalDate.class)))
                .thenThrow(new NoSuchElementException(
                        String.format("Failed to find curId by %s and %s", "ALL", LocalDate.of(2024, 06, 28))
                ));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                currencyService.findByCurrencyAbbreviation("ALL", LocalDate.of(2024, 06, 28)));
        assertThat(exception)
                .hasMessageContaining("Failed to find curId by ALL and 2024-06-28");
    }

}