package com.biblioteca.multa_service.mapper;
import org.springframework.stereotype.Component;

import com.biblioteca.multa_service.dto.MultaRequest;
import com.biblioteca.multa_service.dto.MultaResponse;
import com.biblioteca.multa_service.model.Multa;

/**
 * Clase que proporciona métodos para mapear entre objetos de tipo Multa,
 * MultaRequest y MultaResponse.
 */

@Component
public class MultaMapper {
    //Request a Entity
    public Multa fromRequest(MultaRequest request){
        return Multa.builder()
        .id(request.getId())
        .monto(request.getMonto())
        .motivo(request.getMotivo())
        .usuarioId(request.getUsuarioId())
        .build();
    }

    //Entity a Response
    public MultaResponse toResponse(Multa multa) {
        return MultaResponse.builder()
                .monto(multa.getMonto())
                .motivo(multa.getMotivo())
                .usuarioId(multa.getUsuarioId())
                .build();
    }

}
