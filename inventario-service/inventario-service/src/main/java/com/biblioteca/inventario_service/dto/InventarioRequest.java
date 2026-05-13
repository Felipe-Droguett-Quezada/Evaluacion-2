package com.biblioteca.inventario_service.dto;

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
public class InventarioRequest {

    @NotBlank(message = "Stock es requerido")
    @Size(max = 100, message = "El stock tiene como maximo 100 caracteres")
    private Integer stock;

}
