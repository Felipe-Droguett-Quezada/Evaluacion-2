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

@Service
@Slf4j
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    // LISTAR
    public List<Inventario> listarInventario(){
        return inventarioRepository.findAll();
    }

    // GUARDAR
    public Inventario guardarInventario(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    // BUSCAR
    public Inventario buscarInventario(Long id){
        return inventarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    // ACTUALIZAR
    public InventarioResponse actualizarInventario(Long id, InventarioRequest inventarioRequest) {
        log.info("Actualizando el inventario con el id: {}", id);
        Inventario existeInventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro el Inventario con el id: " + id));

        existeInventario.setStock(inventarioRequest.getStock());
        Inventario inventarioActualizado = inventarioRepository.save(existeInventario);
        return InventarioMapper.toResponse(inventarioActualizado);
    }

    // ELIMINAR
    public void eliminarInventario(Long id){
        inventarioRepository.deleteById(id);
    }
}