package com.biblioteca.categoria_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Value("${services.categorias.url}")
    private String categoriasServiceUrl;

    @Bean
    public WebClient categoriasWebClient(){
        return WebClient.builder()
                    .baseUrl(categoriasServiceUrl)
                    .defaultHeader("Content-Type", "application/json")
                    .defaultHeader("Accept", "application/json")
                    .build();
    }

}
