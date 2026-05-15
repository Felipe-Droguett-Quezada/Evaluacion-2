package com.biblioteca.autor_service.controller;

import com.biblioteca.autor_service.dto.AutorRequest;
import com.biblioteca.autor_service.dto.AutorResponse;
import com.biblioteca.autor_service.model.Autor;
import com.biblioteca.autor_service.service.AutorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;



import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/autores")
@Slf4j
public class AutorController {

    @Autowired
    private AutorService autorService;

    // GET
    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores(){
        log.info("GET /autor - Solicitud para listar todos los autores");
        return ResponseEntity.ok(autorService.listarAutores());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutor(@PathVariable Long id){
        log.info("GET /autor/{}",id,"Solicitamos autores por ID: ",id);
        return ResponseEntity.ok(autorService.buscarAutor(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<Autor> guardarAutor(@RequestBody Autor autor){
        return ResponseEntity.ok(autorService.guardarAutor(autor));
    }

    //PUT

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> actualizarAutor(@PathVariable Long id, @Valid @RequestBody AutorRequest autor){
        log.info("PUT /autor/{}",id,"Actualizado el autor con ID: ",id);
        return ResponseEntity.ok(autorService.actualizarAutor(id,autor));
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id){
        log.info("DELETE /autores/{}",id);
        autorService.eliminarAutor(id);
        return ResponseEntity.ok("Autor eliminado");
    }
}