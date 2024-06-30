package com.michalenok.currency_rate_guide.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record RateResponse(
        /**
         * internal code
         */
        @JsonProperty("Cur_ID") String curId,
        /**
         * date for which the rate is requested
         */
        @JsonProperty("Date") LocalDate date,
        /**
         *  exchange rate
         */
        @JsonProperty("Cur_OfficialRate") String curOfficialRate) {
}
