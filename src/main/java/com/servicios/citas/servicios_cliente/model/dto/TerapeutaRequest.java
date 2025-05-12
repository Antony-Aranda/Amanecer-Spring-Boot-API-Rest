package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Terapeuta-Request", description = "Datos para crear o actualizar un Terapeuta")
public class TerapeutaRequest {
    @NotEmpty(message = "Nombre vacio")
    @Schema(description = "Nombre", example = "Antony", required = true)
    private  String nombre;

    @NotEmpty(message = "Apellido vacio")
    @Schema(description = "Apellido del terpeuta", example = "Aranda", required = true)
    private String apellido;

    @NotEmpty(message = "Dni vacio")
    @Schema(description = "dni del terpeuta", example = "73745091", required = true)
    private String dni;
}
