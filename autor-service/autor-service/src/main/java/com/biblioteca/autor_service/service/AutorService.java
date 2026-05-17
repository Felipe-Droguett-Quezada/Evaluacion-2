package com.biblioteca.autor_service.service;

import com.biblioteca.autor_service.dto.AutorRequest;
import com.biblioteca.autor_service.dto.AutorResponse;
import com.biblioteca.autor_service.mapper.AutorMapper;
import com.biblioteca.autor_service.model.Autor;
import com.biblioteca.autor_service.repository.AutorRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Slf4j
public class AutorService {



    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private AutorMapper autorMapper;


    // LISTAR
    /**
     * Lista todos los autores registrados en el sistema
     * 
     * @return Lista de AutorResponse con todos los autores
     */
    public List<AutorResponse> listarAutores() {
        log.info("Listando todos los autores");
        List<Autor> autores = autorRepository.findAll();
        return autores.stream()
                .map(autorMapper::toResponse)
                .toList();
    }

    // GUARDAR
    /**
     * Guarda un nuevo autor en el sistema
     * 
     * @param request Datos del autor a guardar
     * @return AutorResponse con los datos del autor guardado
     * @throws IllegalArgumentException Si ya existe un autor con el mismo nombre
     */
    public AutorResponse guardarAutor(AutorRequest request) {
        log.info("Guardando nuevo autor: {}", request.getNombre());

        // Validar nombre duplicado (regla de negocio)
        if (autorRepository.existsByNombre(request.getNombre())) {
            log.warn("Intento de duplicado - Autor ya existe: {}", request.getNombre());
            throw new IllegalArgumentException("Ya existe un autor con el nombre: " + request.getNombre());
        }

        Autor autor = autorMapper.toEntity(request);
        Autor savedAutor = autorRepository.save(autor);
        log.info("Autor guardado exitosamente con ID: {}", savedAutor.getId());
        return autorMapper.toResponse(savedAutor);
    }

    // BUSCAR
    /**
     * Busca un autor por su ID
     * 
     * @param id ID del autor a buscar
     * @return AutorResponse con los datos del autor
     * @throws NoSuchElementException Si no se encuentra el autor
     */
    public AutorResponse buscarAutor(Long id) {
        log.info("Buscando autor con ID: {}", id);
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el autor con ID: " + id));
        return autorMapper.toResponse(autor);
    }

    /**
     * Actualiza los datos de un autor existente
     * 
     * @param id           ID del autor a actualizar
     * @param autorRequest Nuevos datos del autor
     * @return AutorResponse con los datos actualizados
     * @throws NoSuchElementException   Si no se encuentra el autor
     * @throws IllegalArgumentException Si el nuevo nombre ya existe en otro autor
     */
    public AutorResponse actualizarAutor(Long id, AutorRequest autorRequest) {
        log.info("Actualizando autor con el id: {}", id);
        Autor existeAutor = autorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el autor con el id: " + id));

        // Validar nombre duplicado SOLO si cambió el nombre
        if (!existeAutor.getNombre().equals(autorRequest.getNombre()) &&
                autorRepository.existsByNombre(autorRequest.getNombre())) {
            log.warn("Intento de actualización - Nombre duplicado: {}", autorRequest.getNombre());
            throw new IllegalArgumentException("Ya existe otro autor con el nombre: " + autorRequest.getNombre());
        }

        existeAutor.setNombre(autorRequest.getNombre());
        existeAutor.setNacionalidad(autorRequest.getNacionalidad());

        Autor autorActualizado = autorRepository.save(existeAutor);
        log.info("Autor actualizado exitosamente con ID: {}", autorActualizado.getId());
        return autorMapper.toResponse(autorActualizado);
    }

    // ELIMINAR 
    /**
     * Elimina un autor del sistema
     * 
     * @param id ID del autor a eliminar
     * @throws NoSuchElementException Si no se encuentra el autor
     */
    public void eliminarAutor(Long id) {
        log.info("Eliminando autor con ID: {}", id);

        if (!autorRepository.existsById(id)) {
            throw new NoSuchElementException("No se encontró el autor con ID: " + id);
        }

        autorRepository.deleteById(id);
        log.info("Autor eliminado exitosamente con ID: {}", id);
    }
}