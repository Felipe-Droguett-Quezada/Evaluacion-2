package com.biblioteca.inventario_service.repository;

import com.biblioteca.inventario_service.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    /**
     * Verifica si existe un autor con el nombre proporcionado.
     * Utilizado para validar reglas de negocio (evitar duplicados).
     * 
     * @param nombreInventario String - El nombre del inventario a verificar
     * @return boolean - true si existe, false en caso contrario
     */
    boolean existsByNombreInventario(String nombreInventario);
}
