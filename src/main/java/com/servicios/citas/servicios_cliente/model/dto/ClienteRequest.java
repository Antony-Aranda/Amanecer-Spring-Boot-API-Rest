package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {
    @NotEmpty(message = "Nombre vacio")
    private String nombre;
    @NotEmpty(message = "Apellido vacio")
    private String apellido;
    @NotEmpty(message = "Dni vacio")
    private String dni;
    @NotEmpty(message = "Telefono vacio")
    private String telefono;
    @NotEmpty(message = "Genero vacio")
    private String genero;
    @NotNull(message = "Estado vacio")
    private int estado;
    @NotNull(message = "Usuario no elegido")
    private Long id_usuario;        // <-- solo el ID del usuario
    // getters/setters...
}
