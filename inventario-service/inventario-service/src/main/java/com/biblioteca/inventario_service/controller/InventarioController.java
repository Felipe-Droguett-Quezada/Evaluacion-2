package com.biblioteca.inventario_service.controller;

import com.biblioteca.inventario_service.model.Inventario;
import com.biblioteca.inventario_service.service.InventarioService;
import com.biblioteca.inventario_service.dto.InventarioRequest;
import com.biblioteca.inventario_service.dto.InventarioResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/inventario")
@Slf4j
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // GET
    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        log.info("GET /inventario - Solicitud para listar todo el inventario");
        return ResponseEntity.ok(inventarioService.listarInventario());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscar(@PathVariable Long id) {
        log.info("GET /inventario/{}", id, "Solicitamos inventario por ID: ", id);
        return ResponseEntity.ok(inventarioService.buscarInventario(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<Inventario> guardar(@RequestBody Inventario inventario){
        return ResponseEntity.ok(inventarioService.guardarInventario(inventario));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponse> actualizarInventario(@PathVariable Long id,@Valid @RequestBody InventarioRequest inventario) {
        log.info("PUT /inventario/{}", id, "Actualizado el inventario con ID: ", id);
        return ResponseEntity.ok(inventarioService.actualizarInventario(id, inventario));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){inventarioService.eliminarInventario(id);
        return ResponseEntity.ok("Inventario eliminado");
    }
}