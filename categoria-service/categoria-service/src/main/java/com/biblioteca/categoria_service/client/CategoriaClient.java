package com.biblioteca.categoria_service.client;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.biblioteca.categoria_service.dto.CategoriaResponse;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Component
@Slf4j
public class CategoriaClient {

    @Autowired
    private WebClient categoriasWebClient;

        /**
     * Obtiene los préstamos asociados a un usuario por su ID utilizando el
     * WebClient para hacer una solicitud GET al servicio de préstamos.
     * 
     * @param id Long - El ID del usuario para el cual se desean obtener los
     *               préstamos
     * @return List<LoanResponse> - La lista de préstamos asociados al usuario
     *         obtenidos del servicio de préstamos
     */
    public List<CategoriaResponse> getCategoriaById(Long id) {
        log.info("Obtener categoria con id: {}", id);
        try {
            return categoriasWebClient.get()
                    .uri("/categoria/{id}", id)
                    .retrieve()
                    .bodyToFlux(CategoriaResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener la categoria con ID {}", id, ex);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new NoSuchElementException("No se encontraron categorias con el ID " + id);
                default -> throw new RuntimeException("Error al obtener la categoria con ID {}" + id, ex);
            }
        }
    }
}


