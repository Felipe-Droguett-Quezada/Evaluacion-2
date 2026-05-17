package com.biblioteca.categoria_service.repository;

import com.biblioteca.categoria_service.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /**
     * Verifica si existe un autor con el nombre proporcionado.
     * Utilizado para validar reglas de negocio (evitar duplicados).
     * 
     * @param nombre String - El nombre del autor a verificar
     * @return boolean - true si existe, false en caso contrario
     */
    boolean existsByNombre(String nombre);

}
