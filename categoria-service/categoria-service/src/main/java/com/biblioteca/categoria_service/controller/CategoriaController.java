package com.biblioteca.categoria_service.controller;

import com.biblioteca.categoria_service.service.CategoriaService;
import com.biblioteca.categoria_service.dto.CategoriaRequest;
import com.biblioteca.categoria_service.dto.CategoriaResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import jakarta.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //GET
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        log.info("GET /categorias - Solicitud para listar todos las categorias");
        List<CategoriaResponse> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarCategoria(@PathVariable Long id) {
        log.info("GET /categorias/{} - Buscando categoria por ID", id);
        CategoriaResponse categoria = categoriaService.buscarCategoria(id);
        return ResponseEntity.ok(categoria);
    }

    //POST
    @PostMapping
    public ResponseEntity<CategoriaResponse> guardarCategoria(@Valid @RequestBody CategoriaRequest request) {
        log.info("POST /categorias - Creando nueva categoria: {}", request.getNombre());
        CategoriaResponse nuevaCategoria = categoriaService.guardarCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(@PathVariable Long id,
            @Valid @RequestBody CategoriaRequest categoria) {
        log.info("PUT /categorias/{} - Actualizando la categoria con ID: {}", id);
        CategoriaResponse categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        log.info("DELETE /categorias/{} - Eliminando categoria", id);
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("Categoria eliminado correctamente");
    }
}
