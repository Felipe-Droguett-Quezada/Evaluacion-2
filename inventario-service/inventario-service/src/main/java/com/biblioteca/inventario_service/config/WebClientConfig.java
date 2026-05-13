package com.biblioteca.inventario_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Value("${services.inventarios.url}")
    private String inventariosServiceUrl;

    @Bean
    public WebClient inventariosWebClient(){
        return WebClient.builder()
                    .baseUrl(
                        inventariosServiceUrl)
                    .defaultHeader("Content-Type", "application/json")
                    .defaultHeader("Accept", "application/json")
                    .build();
    }

}
