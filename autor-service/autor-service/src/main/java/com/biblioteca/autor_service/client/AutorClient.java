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
     * Obtiene un autor por su ID (singular)
     * 
     * @param id ID del autor a buscar
     * @return AutorResponse - Un solo autor
     * @throws NoSuchElementException Si no existe el autor
     */
    public AutorResponse getAutorById(Long id) { 
        log.info("Obteniendo autor con ID {}", id);
        try {
            return autoresWebClient.get()
                    .uri("/autores/{id}", id)
                    .retrieve()
                    .bodyToMono(AutorResponse.class) 
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener el autor con ID {}", id, ex);
            if (ex.getStatusCode().value() == 404) {
                throw new NoSuchElementException("No se encontró el autor con ID: " + id); 
            }
            throw new RuntimeException("Error al obtener el autor con ID " + id, ex);
        }
    }
    
    /**
     * Obtiene todos los autores registrados
     * 
     * @return List<AutorResponse> - Lista de todos los autores
     */
    public List<AutorResponse> getAllAutores() {
        log.info("Obteniendo todos los autores");
        try {
            return autoresWebClient.get()
                    .uri("/autores") // ✅ Sin /{id}
                    .retrieve()
                    .bodyToFlux(AutorResponse.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error al obtener todos los autores", ex);
            throw new RuntimeException("Error al obtener todos los autores", ex);
        }
    }
}

