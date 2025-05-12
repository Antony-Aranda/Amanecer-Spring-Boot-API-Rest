package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema(name = "Cita-Request", description = "Datos para crear o actualizar una cita")
public class CitaRequest {
    @NotNull(message = "Nombre vacio")
    @Schema(description = "Especificar una fecha en formato date", example = "2025-09-10", required = true)
    private LocalDateTime fecha_reserva;

    @NotNull(message = "estado vacio")
    @Schema(description = "Estado de la cita", example = "0", required = true)
    private int estado;

    @NotEmpty(message = "Nombre vacio")
    @Schema(description = "Duracion de la session completa", example = "00:45", required = true)
    private String duracion_total;

    @NotNull(message = "total vacio")
    @Schema(description = "Total a apagar", example = "900.11", required = true)
    private BigDecimal total;

    @NotNull(message = "cliente vacio")
    @Schema(description = "Define el cliente", example = "1", required = true)
    private long id_cliente;
}
