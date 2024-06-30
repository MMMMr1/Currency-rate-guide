package com.michalenok.currency_rate_guide.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * Configuration for Open API
 */
@Configuration
@Profile("!test")
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenApi(
            @Value("${openapi.service.name}") String appName,
            @Value("${openapi.service.version}") String serviceVersion,
            @Value("${openapi.service.description}") String appDescription,
            @Value("${openapi.service.url}") String url
    ) {
        return new OpenAPI()
                .info(new Info().title(appName)
                        .version(serviceVersion)
                        .description(appDescription))
                .servers(List.of(new Server().url(url)));
    }
}
