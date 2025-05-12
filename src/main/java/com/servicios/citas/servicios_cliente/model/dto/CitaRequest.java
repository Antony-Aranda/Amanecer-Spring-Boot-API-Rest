package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CitaRequest {
    @NotNull(message = "Nombre vacio")
    private LocalDateTime fecha_reserva;
    @NotNull(message = "estado vacio")
    private int estado;
    @NotEmpty(message = "Nombre vacio")
    private String duracion_total;
    @NotNull(message = "total vacio")
    private BigDecimal total;
    @NotNull(message = "cliente vacio")
    private long id_cliente;
}
