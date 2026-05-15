package com.biblioteca.autor_service.client;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.biblioteca.autor_service.dto.AutorResponse;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Component
@Slf4j
public class AutorClient {

    @Autowired
    private WebClient autoresWebClient;

        /**
     * Obtiene los préstamos asociados a un usuario por su ID utilizando el
     * WebClient para hacer una solicitud GET al servicio de préstamos.
     * 
     * @param id Long - El ID del usuario para el cual se desean obtener los
     *               préstamos
     * @return List<LoanResponse> - La lista de préstamos asociados al usuario
     *         obtenidos del servicio de préstamos
     */
    public List<AutorResponse> getAutorById(Long id) {
        log.info("Obteniendo autor con ID {}", id);
        try {
            return autoresWebClient.get()
                    .uri("/autores/{id}", id)
                    .retrieve()
                    .bodyToFlux(AutorResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener el autor con ID {}", id, ex);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new NoSuchElementException("No se encontraron autores con el ID " + id);
                default -> throw new RuntimeException("Error al obtener el autor con ID " + id, ex);
            }
        }
    }
}

