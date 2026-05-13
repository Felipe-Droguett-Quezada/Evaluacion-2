package com.biblioteca.prestamo_service.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.biblioteca.prestamo_service.client.LibroClient;
import com.biblioteca.prestamo_service.client.UsuarioClient;
//Valida el prestamo
@Component
public class PrestamoValidator {
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private LibroClient libroClient;
    public void validarPrestamo(Long usuarioId, Long libroId){
        usuarioClient.validarUsuario(usuarioId);
        libroClient.validarLibro(libroId);
    }
}