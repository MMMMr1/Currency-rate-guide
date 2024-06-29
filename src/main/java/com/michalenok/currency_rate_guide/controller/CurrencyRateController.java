package com.michalenok.currency_rate_guide.controller;

import com.michalenok.currency_rate_guide.model.dto.RateResponse;
import com.michalenok.currency_rate_guide.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    @GetMapping("/rates/{curID}")
    public RateResponse getRates(@PathVariable (required = false) String curID,
                                 @RequestParam (name = "ondate", required = false) Date ondate,
                                 @RequestParam (name = "parammode", defaultValue = "0", required = false) int parammode) {
        return rateService.getRate(curID, ondate, parammode);
    }
}
