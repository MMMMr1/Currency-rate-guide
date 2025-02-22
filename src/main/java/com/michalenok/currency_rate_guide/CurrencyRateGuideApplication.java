package com.michalenok.currency_rate_guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CurrencyRateGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateGuideApplication.class, args);
	}

}
