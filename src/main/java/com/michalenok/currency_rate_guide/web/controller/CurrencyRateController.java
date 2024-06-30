package com.michalenok.currency_rate_guide.web.controller;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
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
public class CurrencyRateController {
    private final RateService rateService;

    @PostMapping("/rates")
    public void saveRates(@RequestParam(name = "periodicity", required = false, defaultValue = "0") String periodicity,
                          @RequestParam(required = false) String ondate) {
        log.info("POST /rates?periodicity={}&ondate={}", periodicity, ondate);
        rateService.saveRates(periodicity, ondate);
    }

    @GetMapping("/rates/{curId}")
    public RateResponse getRates(@PathVariable String curId,
                                 @RequestParam(name = "ondate") LocalDate ondate,
                                 @RequestParam(name = "parammode", defaultValue = "0", required = false) int parammode) {
        log.info("GET /rates/{}?ondate={}&parammodee={}", curId, ondate, parammode);
        return rateService.getRate(curId, ondate, parammode);
    }
}
