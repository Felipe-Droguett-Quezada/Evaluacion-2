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

        /**
     * Obtiene los préstamos asociados a un usuario por su ID utilizando el
     * WebClient para hacer una solicitud GET al servicio de préstamos.
     * 
     * @param id Long - El ID del usuario para el cual se desean obtener los
     *               préstamos
     * @return List<LoanResponse> - La lista de préstamos asociados al usuario
     *         obtenidos del servicio de préstamos
     */
    public List<InventarioResponse> getInventarioById(Long id) {
        log.info("Obteniendo Inventario con ID {}", id);
        try {
            return inventariosWebClient.get()
                    .uri("/inventario/{id}", id)
                    .retrieve()
                    .bodyToFlux(InventarioResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener el Inventario con ID {}", id, ex);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new NoSuchElementException("No se encontraron inventarios con el ID  " + id);
                default -> throw new RuntimeException("Error al obtener el Inventario con ID {}" + id, ex);
            }
        }
    }
}
