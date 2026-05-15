package com.biblioteca.autor_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorResponse {
    private Long id;
    private String nombre;
    private String nacionalidad;
}
