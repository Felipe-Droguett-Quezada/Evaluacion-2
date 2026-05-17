package com.biblioteca.autenticacion_service.service;

import com.biblioteca.autenticacion_service.model.Autenticacion;
import com.biblioteca.autenticacion_service.repository.AutenticacionRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.biblioteca.autenticacion_service.dto.AutenticacionRequest;
import com.biblioteca.autenticacion_service.dto.AutenticacionResponse;
import com.biblioteca.autenticacion_service.mapper.AutenticacionMapper;
import java.util.NoSuchElementException;


@Service
@Slf4j
public class AutenticacionService {

    @Autowired
    private AutenticacionRepository autenticacionRepository;

    @Autowired
    private AutenticacionMapper autenticacionMapper;

    //Listar
    public List<AutenticacionResponse> listarUsuarios() {
        log.info("Listando todos los usuarios");
        return autenticacionRepository.findAll()
                .stream()
                .map(autenticacionMapper::toResponse)
                .toList();
    }
    // Crear
    public AutenticacionResponse guardarUsuario(AutenticacionRequest request) {
        log.info("Guardando nuevo usuario: {}", request.getNombreUsuario());

        // Regla de negocio: validar nombre único
        if (autenticacionRepository.existsByNombreUsuario(request.getNombreUsuario())) {
            log.warn("Intento de duplicado - Usuario ya existe: {}", request.getNombreUsuario());
            throw new IllegalArgumentException("Ya existe un usuario con el nombre: " + request.getNombreUsuario());
        }

        Autenticacion autenticacion = autenticacionMapper.toEntity(request);
        Autenticacion guardado = autenticacionRepository.save(autenticacion);
        log.info("Usuario guardado exitosamente con ID: {}", guardado.getId());
        return autenticacionMapper.toResponse(guardado);
    }

    //buscar por id
    public AutenticacionResponse buscarUsuario(Long id) {
        log.info("Buscando usuario con ID: {}", id);
        Autenticacion autenticacion = autenticacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el usuario con ID: " + id));
        return autenticacionMapper.toResponse(autenticacion);
    }

    //Actualizar
    public AutenticacionResponse actualizarUsuario(Long id, AutenticacionRequest request) {
        log.info("Actualizando usuario con ID: {}", id);

        Autenticacion existeUsuario = autenticacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el usuario con ID: " + id));

        // Validar nombre duplicado SOLO si cambió el nombre
        if (!existeUsuario.getNombreUsuario().equals(request.getNombreUsuario()) &&
                autenticacionRepository.existsByNombreUsuario(request.getNombreUsuario())) {
            log.warn("Intento de actualización - Nombre duplicado: {}", request.getNombreUsuario());
            throw new IllegalArgumentException("Ya existe otro usuario con el nombre: " + request.getNombreUsuario());
        }

        existeUsuario.setNombreUsuario(request.getNombreUsuario());
        existeUsuario.setPassword(request.getPassword());

        Autenticacion actualizado = autenticacionRepository.save(existeUsuario);
        log.info("Usuario actualizado exitosamente con ID: {}", actualizado.getId());
        return autenticacionMapper.toResponse(actualizado);
    }

    //Delete
    public void eliminarUsuario(Long id) {
        log.info("Eliminando usuario con ID: {}", id);

        if (!autenticacionRepository.existsById(id)) {
            throw new NoSuchElementException("No se encontró el usuario con ID: " + id);
        }

        autenticacionRepository.deleteById(id);
        log.info("Usuario eliminado exitosamente con ID: {}", id);
    }
}