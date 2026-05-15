package com.biblioteca.categoria_service.service;

import com.biblioteca.categoria_service.model.Categoria;
import com.biblioteca.categoria_service.repository.CategoriaRepository;
import com.biblioteca.categoria_service.dto.CategoriaRequest;
import com.biblioteca.categoria_service.dto.CategoriaResponse;
import com.biblioteca.categoria_service.mapper.CategoriaMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    //GUARDAR
    public Categoria guardarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    //BUSCAR
    public Categoria buscarCategoria(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    // ACTUALIZAR
    public CategoriaResponse actualizarCategoria(Long id, CategoriaRequest categoriaRequest) {
        log.info("Actualizando la categoria con el id: {}", id);
        Categoria existeCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro la categoria con el id: " + id));

        existeCategoria.setNombre(categoriaRequest.getNombre());

        Categoria categoriaActualizado = categoriaRepository.save(existeCategoria);
        return categoriaMapper.toResponse(categoriaActualizado);
    }

    //ELIMINAR
    public void eliminarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}
