package com.michalenok.currency_rate_guide.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrencyResponse(
        /**
         * internal code
         */
        @JsonProperty("Cur_ID") String curId,
        /**
         * numeric code (ISO 4217)
         */
        @JsonProperty("Cur_Code") String curCode,
        /**
         * alphabetic code (ISO 4217)
         */
        @JsonProperty("Cur_Abbreviation") String curAbbreviation,
        /**
         * currency name in Russian language
         */
        @JsonProperty("Cur_Name") String curName,
        /**
         * date of currency inclusion in the list of currencies to which the official exchange rate of the Belarusian
         * ruble is set
         */
        @JsonProperty("Cur_DateStart") LocalDate curDateStart,
        /**
         * Cur_DateEnd - date of exclusion of the currency from the list of currencies to which the official exchange
         * rate of the Belarusian ruble is set.
         */
        @JsonProperty("Cur_DateEnd") LocalDate curDateEnd) {
}
