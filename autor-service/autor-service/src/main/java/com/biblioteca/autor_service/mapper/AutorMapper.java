package com.biblioteca.autor_service.mapper;

import org.springframework.stereotype.Component;
import com.biblioteca.autor_service.model.Autor;
import com.biblioteca.autor_service.dto.AutorResponse;
import com.biblioteca.autor_service.dto.AutorRequest;

@Component
public class AutorMapper {

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
    public Autor toEntity(AutorRequest request) {
        return Autor.builder()
                .nombre(request.getNombre())
                .nacionalidad(request.getNacionalidad())
                .build();
    }


    /**
     * Convierte una entidad Author a un AuthorResponse. Toma los campos de la
     * entidad Author y los asigna a un nuevo objeto AuthorResponse utilizando el
     * patrón de diseño Builder. Este método se utiliza para mapear los datos de la
     * entidad Author a un objeto de transferencia de datos que se devolverá en las
     * respuestas HTTP.
     * 
     * @param autor Author - La entidad Author que se desea convertir
     * @return AuthorResponse - El objeto de transferencia de datos creado a partir
     *         de la entidad Author
     */
    public AutorResponse toResponse(Autor autor) {
        return AutorResponse.builder()
                .id(autor.getId())
                .nombre(autor.getNombre())                
                .nacionalidad(autor.getNacionalidad())
                .build();
    }
}
