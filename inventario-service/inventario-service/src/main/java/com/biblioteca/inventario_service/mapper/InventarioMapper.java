package com.biblioteca.inventario_service.mapper;

import org.springframework.stereotype.Component;

import com.biblioteca.inventario_service.model.Inventario;

import lombok.Builder;

import com.biblioteca.inventario_service.dto.InventarioRequest;
import com.biblioteca.inventario_service.dto.InventarioResponse;

@Component
public class InventarioMapper {

    /**
     * Convierte un AuthorRequest a una entidad Author. Toma los campos del
     * AuthorRequest y los asigna a un nuevo objeto Author utilizando el patrón de
     * diseño Builder. El ID y la fecha de creación no se establecen en este método,
     * ya que se generarán automáticamente al persistir la entidad en la base de
     * datos. Este método se utiliza para mapear los datos de entrada recibidos en
     * las solicitudes HTTP a la entidad Author que se almacenará en la base de
     * datos.
     * 
     * @param request AuthorRequest - El objeto de solicitud que contiene los datos
     *                del nuevo autor a crear
     * @return Author - La entidad Author creada a partir del AuthorRequest
     */
    public Inventario toEntity(InventarioRequest request) {
        return Inventario.builder()
                .stock(request.getStock())
                .nombreInventario(request.getNombreInventario())
                .build();
    }
        /**
     * Convierte una entidad Author a un AuthorResponse. Toma los campos de la
     * entidad Author y los asigna a un nuevo objeto AuthorResponse utilizando el
     * patrón de diseño Builder. Este método se utiliza para mapear los datos de la
     * entidad Author a un objeto de transferencia de datos que se devolverá en las
     * respuestas HTTP.
     * 
     * @param Inventario Author - La entidad Author que se desea convertir
     * @return AuthorResponse - El objeto de transferencia de datos creado a partir
     *         de la entidad Author
     */
    public InventarioResponse toResponse(Inventario inventario) {
        return InventarioResponse.builder()
                .id(inventario.getId())
                .stock(inventario.getStock())
                .nombreInventario(inventario.getNombreInventario())                
                .build();
    }


}
