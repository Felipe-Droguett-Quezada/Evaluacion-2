package com.biblioteca.autenticacion_service.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    @Builder.Default
    private List<String> errors = new ArrayList<>();
}
