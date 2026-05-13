package com.biblioteca.multa_service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultaResponse {
    private Long id;
    private Double monto;
    private String motivo;
    private Long usuarioId;
}
