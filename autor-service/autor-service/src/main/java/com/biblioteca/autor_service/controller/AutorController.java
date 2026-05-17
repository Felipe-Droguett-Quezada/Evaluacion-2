package com.biblioteca.autor_service.controller;

import com.biblioteca.autor_service.dto.AutorRequest;
import com.biblioteca.autor_service.dto.AutorResponse;
import com.biblioteca.autor_service.service.AutorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping("/autores")
@Slf4j
public class AutorController {

    @Autowired
    private AutorService autorService;

    // GET
    /**
     * Obtiene la lista completa de todos los autores registrados.
     * 
     * @return ResponseEntity con la lista de autores y código HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<AutorResponse>> listarAutores() {
        log.info("GET /autores - Solicitud para listar todos los autores");
        List<AutorResponse> autores = autorService.listarAutores();
        return ResponseEntity.ok(autores);
    }

    // GET BY ID
    /**
     * Obtiene un autor específico por su ID.
     * 
     * @param id ID del autor a buscar (path variable)
     * @return ResponseEntity con el autor encontrado y código HTTP 200 OK
     * @throws NoSuchElementException Si no existe un autor con el ID proporcionado
     */
    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> buscarAutor(@PathVariable Long id) {
        log.info("GET /autores/{} - Buscando autor por ID", id);
        AutorResponse autor = autorService.buscarAutor(id);
        return ResponseEntity.ok(autor);
    }

    // POST
    /**
     * Crea un nuevo autor en el sistema.
     * 
     * @param request Datos del autor a crear (validados con Bean Validation)
     * @return ResponseEntity con el autor creado y código HTTP 200 OK
     * @throws IllegalArgumentException Si ya existe un autor con el mismo nombre
     */

    @PostMapping
    public ResponseEntity<AutorResponse> guardarAutor(@Valid @RequestBody AutorRequest request) {
        log.info("POST /autores - Creando nuevo autor: {}", request.getNombre());
        AutorResponse nuevoAutor = autorService.guardarAutor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAutor);
    }

    //PUT
    /**
     * Actualiza un autor existente por su ID.
     * 
     * @param id    ID del autor a actualizar
     * @param autor Nuevos datos del autor (validados con Bean Validation)
     * @return ResponseEntity con el autor actualizado y código HTTP 200 OK
     * @throws NoSuchElementException   Si no existe un autor con el ID
     *                                  proporcionado
     * @throws IllegalArgumentException Si el nuevo nombre ya existe en otro autor
     */
    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> actualizarAutor(@PathVariable Long id,@Valid @RequestBody AutorRequest autor) {
        log.info("PUT /autores/{} - Actualizando autor con ID: {}", id);
        AutorResponse autorActualizado = autorService.actualizarAutor(id, autor);
        return ResponseEntity.ok(autorActualizado); 
    }


    // DELETE
    /**
     * Elimina un autor del sistema por su ID.
     * 
     * @param id ID del autor a eliminar
     * @return ResponseEntity con mensaje de confirmación
     * @throws NoSuchElementException Si no existe un autor con el ID proporcionado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id){
        log.info("DELETE /autores/{} - Eliminando autor",id);
        autorService.eliminarAutor(id);
        return ResponseEntity.ok("Autor eliminada correctamente");
    }
}