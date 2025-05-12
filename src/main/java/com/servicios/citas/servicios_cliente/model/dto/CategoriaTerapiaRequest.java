package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaTerapiaRequest {
    @NotEmpty(message = "Nombre vacio")
    private String nombre;
    @NotEmpty(message = "Descripcion vacio")
    private String descripcion;
}
