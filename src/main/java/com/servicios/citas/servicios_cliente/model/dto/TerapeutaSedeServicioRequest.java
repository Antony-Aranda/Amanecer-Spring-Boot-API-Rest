package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerapeutaSedeServicioRequest {
    @NotNull(message = "Terapeuta vacia")
    private long id_terapeuta;
    @NotNull(message = "Sede Servicio vacia")
    private long id_sede_servicio;
}
