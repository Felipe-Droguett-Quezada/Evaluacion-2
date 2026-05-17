package com.biblioteca.autenticacion_service.controller;

import com.biblioteca.autenticacion_service.dto.AutenticacionRequest;
import com.biblioteca.autenticacion_service.dto.AutenticacionResponse;
import com.biblioteca.autenticacion_service.service.AutenticacionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/autenticacion")
@Slf4j
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    //GET
    @GetMapping
    public ResponseEntity<List<AutenticacionResponse>> listarUsuarios() {
        log.info("GET /autenticacion - Listando todos los usuarios");
        return ResponseEntity.ok(autenticacionService.listarUsuarios());
    }

    //GET CON ID
    @GetMapping("/{id}")
    public ResponseEntity<AutenticacionResponse> buscarUsuario(@PathVariable Long id) {
        log.info("GET /autenticacion/{} - Buscando usuario", id);
        return ResponseEntity.ok(autenticacionService.buscarUsuario(id));
    }

    //POST
    @PostMapping
    public ResponseEntity<AutenticacionResponse> guardarUsuario(@Valid @RequestBody AutenticacionRequest request) {
        log.info("POST /autenticacion - Creando usuario: {}", request.getNombreUsuario());
        AutenticacionResponse nuevoUsuario = autenticacionService.guardarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<AutenticacionResponse> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody AutenticacionRequest request) {
        log.info("PUT /autenticacion/{} - Actualizando usuario", id);
        return ResponseEntity.ok(autenticacionService.actualizarUsuario(id, request));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        log.info("DELETE /autenticacion/{} - Eliminando usuario", id);
        autenticacionService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}