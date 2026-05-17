package com.biblioteca.autor_service.repository;

import com.biblioteca.autor_service.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    /**
     * Verifica si existe un autor con el nombre proporcionado.
     * Utilizado para validar reglas de negocio (evitar duplicados).
     * 
     * @param nombre String - El nombre del autor a verificar
     * @return boolean - true si existe, false en caso contrario
     */
    boolean existsByNombre(String nombre);

    /**
     * Busca un autor por su nombre exacto.
     * 
     * @param nombre String - El nombre del autor a buscar
     * @return Optional<Autor> - El autor encontrado o vacío si no existe
     */
    Optional<Autor> findByNombre(String nombre);
}
