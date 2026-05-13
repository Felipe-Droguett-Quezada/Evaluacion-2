package com.biblioteca.autor_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorRequest {

    @NotBlank(message = "Nombre completo es requerido")
    @Size(max = 100, message = "El nombre completo tiene como maximo 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "Nacionalidad es requerido")
    @Size(max = 100,message = "La nacionalidad tiene como maximo 100 caracteres")
    private String nacionalidad;
    
}
