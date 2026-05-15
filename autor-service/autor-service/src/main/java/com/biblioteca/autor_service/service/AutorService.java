package com.biblioteca.autor_service.service;

import com.biblioteca.autor_service.dto.AutorRequest;
import com.biblioteca.autor_service.dto.AutorResponse;
import com.biblioteca.autor_service.mapper.AutorMapper;
import com.biblioteca.autor_service.model.Autor;
import com.biblioteca.autor_service.repository.AutorRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class AutorService {



    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private AutorMapper autorMapper;


    // LISTAR
    public List<Autor> listarAutores(){
        return autorRepository.findAll();
    }

    // GUARDAR
    public Autor guardarAutor(Autor autor){

        return autorRepository.save(autor);
    }

    // BUSCAR
    public Autor buscarAutor(Long id){
        return autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
    }

    //ACTUALIZAR
    public AutorResponse actualizarAutor(Long id, AutorRequest autorRequest){
        log.info("Actualizando autor con el id: {}",id);
        Autor existeAutor = autorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro al Autor con el id: " + id));
        
        existeAutor.setNombre(autorRequest.getNombre());
        existeAutor.setNacionalidad(autorRequest.getNacionalidad());

        Autor autorActualizado = autorRepository.save(existeAutor);
        return autorMapper.toResponse(autorActualizado);
    }

    // ELIMINAR
    public void eliminarAutor(Long id){
        autorRepository.deleteById(id);
    }
}