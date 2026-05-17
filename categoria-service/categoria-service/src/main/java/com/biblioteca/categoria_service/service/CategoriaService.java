package com.biblioteca.categoria_service.service;

import com.biblioteca.categoria_service.dto.CategoriaRequest;
import com.biblioteca.categoria_service.dto.CategoriaResponse;
import com.biblioteca.categoria_service.mapper.CategoriaMapper;
import com.biblioteca.categoria_service.model.Categoria;
import com.biblioteca.categoria_service.repository.CategoriaRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    //LISTAR
    public List<CategoriaResponse> listarCategorias() {
        log.info("Listando todas las categorias");
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toResponse)
                .toList();
    }

    //GUARDAR
    public CategoriaResponse guardarCategoria(CategoriaRequest request) {
        log.info("Guardando nueva categoria: {}", request.getNombre());
        // Validar nombre duplicado
        if (categoriaRepository.existsByNombre(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + request.getNombre());
        }
        Categoria categoria = categoriaMapper.toEntity(request);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        log.info("Categoria guardada exitosamente con ID: {}", savedCategoria.getId());
        return categoriaMapper.toResponse(savedCategoria);
    }

    //BUSCAR
    public CategoriaResponse buscarCategoria(Long id) {
        log.info("Buscando categoria con ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la categoria con ID: " + id));
        return categoriaMapper.toResponse(categoria);
    }

    // ACTUALIZAR
    public CategoriaResponse actualizarCategoria(Long id, CategoriaRequest categoriaRequest) {
        log.info("Actualizando la categoria con el id: {}", id);
        Categoria existeCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la categoría con el id: " + id));

        // Validar nombre duplicado SOLO si cambió el nombre
        if (!existeCategoria.getNombre().equals(categoriaRequest.getNombre()) &&
                categoriaRepository.existsByNombre(categoriaRequest.getNombre())) {
            log.warn("Intento de actualización - Nombre duplicado: {}", categoriaRequest.getNombre());
            throw new IllegalArgumentException(
                    "Ya existe otra categoría con el nombre: " + categoriaRequest.getNombre());
        }

        existeCategoria.setNombre(categoriaRequest.getNombre());
        Categoria categoriaActualizado = categoriaRepository.save(existeCategoria);
        log.info("Categoria actualizada exitosamente con ID: {}", categoriaActualizado.getId());
        return categoriaMapper.toResponse(categoriaActualizado);
    }

    //ELIMINAR
    public void eliminarCategoria(Long id) {
        log.info("Eliminando categoria con ID: {}", id);

        if (!categoriaRepository.existsById(id)) {
            throw new NoSuchElementException("No se encontró la categoria con ID: " + id);
        }

        categoriaRepository.deleteById(id);
        log.info("Categoria eliminado exitosamente con ID: {}", id);
    }
}
