package com.biblioteca.categoria_service.controller;

import com.biblioteca.categoria_service.model.Categoria;
import com.biblioteca.categoria_service.service.CategoriaService;
import com.biblioteca.categoria_service.dto.CategoriaRequest;
import com.biblioteca.categoria_service.dto.CategoriaResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Slf4j
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //GET
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        log.info("GET /categoria - Solicitud para listar todas las categorias");
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id) {
        log.info("GET /categori/{}", id, "Solicitamos las categorias por ID: ", id);
        return ResponseEntity.ok(categoriaService.buscarCategoria(id));
    }

    //POST
    @PostMapping
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.guardarCategoria(categoria));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(@PathVariable Long id,@Valid @RequestBody CategoriaRequest categoria) {
        log.info("PUT /categoria/{}", id, "Actualizada la categoria con ID: ", id);
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoria));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        log.info("DELETE /categorias/{}", id);
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("Categoria eliminado");
    }
}
