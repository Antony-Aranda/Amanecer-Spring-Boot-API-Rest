package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SedeServicioRequest {
    @NotNull(message = "Sede vacia")
    private long id_sede;
    @NotNull(message = "Servicio vacia")
    private long id_servicio;

}
