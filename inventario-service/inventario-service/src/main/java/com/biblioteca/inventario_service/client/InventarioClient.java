package com.biblioteca.inventario_service.client;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.biblioteca.inventario_service.dto.InventarioResponse;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Component
@Slf4j
public class InventarioClient {

    @Autowired
    private WebClient inventariosWebClient;


    public InventarioResponse getInventarioById(Long id) {
        log.info("Obteniendo inventario con ID {}", id);
        try {
            return inventariosWebClient.get()
                    .uri("/inventarios/{id}", id) 
                    .retrieve()
                    .bodyToMono(InventarioResponse.class) 
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener el inventario con ID {}", id, ex);
            if (ex.getStatusCode().value() == 404) {
                throw new NoSuchElementException("No se encontró el inventario con ID: " + id);
            }
            throw new RuntimeException("Error al obtener el inventario con ID " + id, ex);
        }
    }
}
