package com.michalenok.currency_rate_guide.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 4567)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/data.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/clear_data.sql", executionPhase = AFTER_TEST_METHOD)
class CurrencyRateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @SneakyThrows
    @CsvSource(value = {"/api/v1/rates : __file/data/CurrencyList.json : __file/data/RateListWithDefaultDate.json",
            "/api/v1/rates?ondate=2024-06-28 : __file/data/CurrencyList.json : __file/data/RateListWithDate_2024-06-28.json",
            "/api/v1/rates?periodicity=1&ondate=2024-06-28 : __file/data/CurrencyList.json : __file/data/RateListWithPeriodicity_1_Date_2024-06-28.json"
    }, delimiter = ':')
    void saveRates_Successful(String url, String fileCurrencies, String fileRates) {
        initCurrencySearchInfo(fileCurrencies);
        initRateSearchInfo(fileRates);
        this.mockMvc.perform(post(url))
                .andExpect(status().isOk());
    }
    @ParameterizedTest
    @SneakyThrows
    @CsvSource(value = {"/api/v1/rates/451?ondate=2024-06-30 : __file/data/response/RateEUR.json",
                        "/api/v1/rates/431?ondate=2024-06-30 : __file/data/response/RateUSD.json" }, delimiter = ':')
    void getRatesWithCurId_Successful(String url, String file) {
        RateResponse rateResponse = objectMapper.readValue(TestUtil.load(file),
                RateResponse.class);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(rateResponse)));
    }

    @ParameterizedTest
    @SneakyThrows
    @CsvSource(value = {"/api/v1/rates/978?parammode=1&ondate=2024-06-30 : __file/data/response/RateEUR.json",
                         "/api/v1/rates/840?parammode=1&ondate=2024-06-30 : __file/data/response/RateUSD.json" }, delimiter = ':')
    void getRatesWithCurCode_Successful(String url, String file) {
        RateResponse rateResponse = objectMapper.readValue(TestUtil.load(file),
                RateResponse.class);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(rateResponse)));
    }

    @ParameterizedTest
    @SneakyThrows
    @CsvSource(value = {"/api/v1/rates/EUR?parammode=2&ondate=2024-06-30 : __file/data/response/RateEUR.json",
                        "/api/v1/rates/USD?parammode=2&ondate=2024-06-30 : __file/data/response/RateUSD.json" }, delimiter = ':')
    void getRatesWithCurAbbreviation_Successful(String url, String file) {
        RateResponse rateResponse = objectMapper.readValue(TestUtil.load(file),
                RateResponse.class);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(rateResponse)));
    }

    private void initCurrencySearchInfo(String fileName) {
        String json = TestUtil.load(fileName);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/exrates/currencies"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(json)
                ));
    }

    private void initRateSearchInfo(String fileName) {
        String json = TestUtil.load(fileName);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/exrates/rates"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(json)
                ));
    }
}