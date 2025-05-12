package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Schema(name = "RangoDuracion-Request", description = "Datos para crear o actualizar una Duracion")
public class RangoDuracionRequest {
    @NotNull(message = "Duración no puede ser nula")
    @Schema(description = "Especificar el tiempo que hay", example = "1:00:00", required = true)
    private LocalTime duracion;
}
