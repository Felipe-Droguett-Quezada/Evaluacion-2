package com.biblioteca.inventario_service.controller;

import com.biblioteca.inventario_service.service.InventarioService;
import com.biblioteca.inventario_service.dto.InventarioRequest;
import com.biblioteca.inventario_service.dto.InventarioResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/inventarios")
@Slf4j
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // GET
    @GetMapping
    public ResponseEntity<List<InventarioResponse>> listarInventarios() {
        log.info("GET /inventarios - Solicitud para listar todos los inventarios");
        List<InventarioResponse> inventarios = inventarioService.listarInventarios();
        return ResponseEntity.ok(inventarios);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponse> buscarInventario(@PathVariable Long id) {
        log.info("GET /inventarios/{} - Buscando inventario con ID", id);
        InventarioResponse inventario = inventarioService.buscarInventario(id);
        return ResponseEntity.ok(inventario);
    }

    // POST
    @PostMapping
    public ResponseEntity<InventarioResponse> guardarInventario(@Valid @RequestBody InventarioRequest request) {
        log.info("POST /inventarios - Creando nuevo inventario: {}", request.getNombreInventario());
        InventarioResponse nuevoInventario = inventarioService.guardarInventario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInventario);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponse> actualizarInventario(@PathVariable Long id,@Valid @RequestBody InventarioRequest inventario) {
        log.info("PUT /inventarios/{} - Actualizando el inventario", id);
        InventarioResponse inventarioActualizado = inventarioService.actualizarInventario(id, inventario);
        return ResponseEntity.ok(inventarioActualizado);
    }

    // DELETE    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Long id) {
        log.info("DELETE /inventarios/{} - Eliminando el inventario", id);
        inventarioService.eliminarInventario(id);
        return ResponseEntity.ok("Inventario eliminado correctamente");
    }
}