package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Schema(name = "Sede-Request", description = "Datos para crear o actualizar una Sede")
public class SedeRequest {
    @NotEmpty(message = "Nombre vacio")
    @Schema(description = "Nombre de la sede", example = "Puerto sede", required = true)
    private  String nombre;

    @NotEmpty(message = "direccion vacio")
    @Schema(description = "Dirrecion ", example = "lima", required = true)
    private  String direccion;

    @NotEmpty(message = "distrito vacio")
    @Schema(description = "distrito de la sede", example = "Lima", required = true)
    private  String distrito;

    @NotEmpty(message = "departamento vacio")
    @Schema(description = "Departamento de la sede", example = "Lima", required = true)
    private  String departamento;

    @NotNull(message = "hora_abierto vacio")
    @Schema(description = "Hora de apertura", example = "07:00:00", required = true)
    private LocalTime hora_abierto;

    @NotNull(message = "hora_cerrado vacio")
    @Schema(description = "Hoara de cierre", example = "22:00:00", required = true)
    private  LocalTime hora_cerrado;
}
