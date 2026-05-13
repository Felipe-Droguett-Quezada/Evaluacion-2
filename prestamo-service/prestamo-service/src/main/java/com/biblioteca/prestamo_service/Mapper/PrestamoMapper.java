package com.biblioteca.prestamo_service.Mapper;
import org.springframework.stereotype.Component;
import com.biblioteca.prestamo_service.dto.PrestamoRequest;
import com.biblioteca.prestamo_service.dto.PrestamoResponse;
import com.biblioteca.prestamo_service.model.Prestamo;
@Component
public class PrestamoMapper {
    public Prestamo fromRequest(PrestamoRequest request) {
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuarioId(request.getUsuarioId());
        prestamo.setLibroId(request.getLibroId());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setEstado(request.getEstado());
        return prestamo;
    }
    public PrestamoResponse toResponse(Prestamo prestamo) {
        return PrestamoResponse.builder()
                .id(prestamo.getId())
                .usuarioId(prestamo.getUsuarioId())
                .libroId(prestamo.getLibroId())
                .fechaPrestamo(prestamo.getFechaPrestamo())
                .estado(prestamo.getEstado())
                .build();
    }
}