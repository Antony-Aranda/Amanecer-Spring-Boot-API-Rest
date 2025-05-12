package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "SedeServicio-Request", description = "Datos para crear o actualizar un SedeServicio")
public class SedeServicioRequest {
    @NotNull(message = "Sede vacia")
    @Schema(description = "Sede a asignar", example = "Puerto sede", required = true)
    private long id_sede;

    @NotNull(message = "Servicio vacia")
    @Schema(description = "Servicio a asignar", example = "Arterial", required = true)
    private long id_servicio;

}
