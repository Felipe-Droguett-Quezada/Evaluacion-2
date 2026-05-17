package com.biblioteca.inventario_service.service;

import com.biblioteca.inventario_service.dto.InventarioRequest;
import com.biblioteca.inventario_service.model.Inventario;
import com.biblioteca.inventario_service.repository.InventarioRepository;
import com.biblioteca.inventario_service.dto.InventarioResponse;
import com.biblioteca.inventario_service.mapper.InventarioMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;

@Service
@Slf4j
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private InventarioMapper inventarioMapper;

    // LISTAR 
    public List<InventarioResponse> listarInventarios() {
        log.info("Listando todos los inventarios");
        List<Inventario> inventarios = inventarioRepository.findAll();
        return inventarios.stream()
                .map(inventarioMapper::toResponse)
                .toList();
    }

    // GUARDAR
    public InventarioResponse guardarInventario(InventarioRequest request) {
        log.info("Guardando nuevo inventario: {}", request.getNombreInventario());

        // Validar nombre duplicado
        if (inventarioRepository.existsByNombreInventario(request.getNombreInventario())) {
            throw new IllegalArgumentException("Ya existe un inventario con el nombre: " + request.getNombreInventario());
        }

        Inventario inventario = inventarioMapper.toEntity(request);
        Inventario savedInventario = inventarioRepository.save(inventario);
        log.info("Inventario guardado exitosamente con ID: {}", savedInventario.getId());
        return inventarioMapper.toResponse(savedInventario);
    }

    // BUSCAR
    public InventarioResponse buscarInventario(Long id) {
        log.info("Buscando inventario con ID: {}", id);
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el inventario con ID: " + id));
        return inventarioMapper.toResponse(inventario);
    }

    // ACTUALIZAR
    public InventarioResponse actualizarInventario(Long id, InventarioRequest inventarioRequest) {
        log.info("Actualizando inventario con el id: {}", id);
        Inventario existeInventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el inventario con el id: " + id));

        // Validar nombre duplicado si cambió
        if (!existeInventario.getNombreInventario().equals(inventarioRequest.getNombreInventario()) &&
                inventarioRepository.existsByNombreInventario(inventarioRequest.getNombreInventario())) {
            throw new IllegalArgumentException("Ya existe otro inventario con el nombre: " + inventarioRequest.getNombreInventario());
        }

        existeInventario.setNombreInventario(inventarioRequest.getNombreInventario());
        existeInventario.setStock(inventarioRequest.getStock());

        Inventario inventarioActualizado = inventarioRepository.save(existeInventario);
        log.info("Inventario actualizado exitosamente con ID: {}", inventarioActualizado.getId());
        return inventarioMapper.toResponse(inventarioActualizado);
    }

    // ELIMINAR
    public void eliminarInventario(Long id) {
        log.info("Eliminando el inventario con ID: {}", id);

        if (!inventarioRepository.existsById(id)) {
            throw new NoSuchElementException("No se encontró el inventario con ID: " + id);
        }

        inventarioRepository.deleteById(id);
        log.info("Inventario eliminado exitosamente con ID: {}", id);
    }
}