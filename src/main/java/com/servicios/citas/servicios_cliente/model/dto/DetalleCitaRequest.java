package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleCitaRequest {
    @NotNull(message = "SubMonto vacio")
    private BigDecimal sub_monto;

    @NotNull(message = "SedeServicio y terapeuta vacio")
    private long id_terapeuta_sede_servicio;

    @NotNull(message = "Bloque vacio")
    private long id_bloque_disponible;
}
