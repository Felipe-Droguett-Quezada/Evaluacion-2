package com.biblioteca.libro_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.biblioteca.libro_service.dto.AuthorResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthorClient {
    
    @Autowired
    private WebClient authorsWebClient;

    /**
     * Obtiene la información de un autor por su ID utilizando el WebClient para
     * hacer una solicitud GET al servicio de autores.
     * 
     * @param authorId Long - El ID del autor para el cual se desea obtener la
     *                 información
     * @return AuthorResponse - La información del autor obtenida del servicio de
     *         autores
     */
    public AuthorResponse getAuthorById(Long authorId) {
        log.info("Fetching author with ID: {}", authorId);
        try {
            return authorsWebClient.get()
                    .uri("/authors/{authorId}", authorId)
                    .retrieve()
                    .bodyToMono(AuthorResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error fetching author with ID {}", authorId);
            switch (ex.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Author not found with ID " + authorId);
                default -> throw new RuntimeException("Error fetching author with ID " + authorId, ex);
            }
        }
    }
}

