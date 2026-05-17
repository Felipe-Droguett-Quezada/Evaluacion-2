package com.biblioteca.autenticacion_service.mapper;

import org.springframework.stereotype.Component;

import com.biblioteca.autenticacion_service.dto.AutenticacionRequest;
import com.biblioteca.autenticacion_service.dto.AutenticacionResponse;
import com.biblioteca.autenticacion_service.model.Autenticacion;

@Component
public class AutenticacionMapper {

    public Autenticacion toEntity(AutenticacionRequest request) {
        return Autenticacion.builder()
                .nombreUsuario(request.getNombreUsuario())
                .password(request.getPassword())
                .builder();
    }

    public AutenticacionResponse toResponse(Autenticacion autenticacion) {
        return Autenticacion.builder()
                .id(autenticacion.getId())
                .nombreUsuario(autenticacion.getNombreUsuario())
                .builder();
    }

}
