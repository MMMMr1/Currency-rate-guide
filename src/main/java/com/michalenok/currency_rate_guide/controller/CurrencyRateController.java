package com.michalenok.currency_rate_guide.controller;

import com.michalenok.currency_rate_guide.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurrencyRateController {
    private final RateService rateService;
    @PostMapping("/rates")
    public void saveRates(@RequestParam(name = "periodicity", required = false, defaultValue = "0") String periodicity,
                          @RequestParam(required = false) String ondate) {
        rateService.saveRates(periodicity, ondate);
    }
}
