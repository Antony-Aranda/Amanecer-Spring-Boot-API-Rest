package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsuarioRequest {
    @NotEmpty(message = "Correo vacio")
    private String correo;
    @NotEmpty(message = "Contraseña vacio")
    private String password;
    @NotNull(message = "estado vacio")
    private int estado;
    @NotEmpty(message = "Nombre vacio")
    private  String nombre;
    @NotEmpty(message = "Rol vacio")
    private  String rol;
}
