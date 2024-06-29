package com.michalenok.currency_rate_guide.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RateResponse(
        @JsonProperty("Cur_ID") String curID,
        @JsonProperty("Date") Date date,
        @JsonProperty("Cur_Abbreviation") String  curAbbreviation,
        @JsonProperty("Cur_OfficialRate") String  curOfficialRate){}
