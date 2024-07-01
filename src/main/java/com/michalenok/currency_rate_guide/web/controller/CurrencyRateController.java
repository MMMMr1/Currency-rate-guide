package com.michalenok.currency_rate_guide.web.controller;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.service.RateService; 
import com.michalenok.currency_rate_guide.validator.ValidLocalDate; 
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class CurrencyRateController {
    private final RateService rateService;

    @Operation(summary = "Save the exchange rate for the day on which the rate is requested")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping("/rates")
    public void saveRates(@RequestParam(name = "periodicity", required = false, defaultValue = "0") String periodicity,
                          @RequestParam(required = false) @Schema(defaultValue = "2024-06-10") String ondate) {
        log.info("POST /rates?periodicity={}&ondate={}", periodicity, ondate);
        rateService.saveRates(periodicity, ondate);
    }

    @Operation(summary = "Get the exchange rate for the day on which the rate is requested")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = RateResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request message ", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "The server cannot find the requested resource", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping("/rates/{curId}")
    public RateResponse getRates(@PathVariable @Schema(defaultValue = "451") String curId,
                                                @RequestParam(name = "ondate") @Schema(defaultValue = "2024-06-10") LocalDate ondate,
                                                @RequestParam(name = "parammode", defaultValue = "0", required = false) @Min(0) @Max(2) int parammode) {
        log.info("GET /rates/{}?ondate={}&parammodee={}", curId, ondate, parammode);
        return rateService.getRate(curId, ondate, parammode);
    }
}
