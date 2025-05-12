package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerapeutaRequest {
    @NotEmpty(message = "Nombre vacio")
    private  String nombre;
    @NotEmpty(message = "Apellido vacio")
    private String apellido;
    @NotEmpty(message = "Dni vacio")
    private String dni;
}
