package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "DeatalleCitas-Request", description = "Datos para crear o actualizar un detalle")
public class DetalleCitaRequest {
    @NotNull(message = "SubMonto vacio")
    @Schema(description = "Digitar un monto", example = "90.00", required = true)
    private BigDecimal sub_monto;

    @NotNull(message = "SedeServicio y terapeuta vacio")
    @Schema(description = "Especificar el terapeuta sede y servicio", example = "1", required = true)
    private long id_terapeuta_sede_servicio;

    @NotNull(message = "Bloque vacio")
    @Schema(description = "Especificar un bloque", example = "1", required = true)
    private long id_bloque_disponible;
}
