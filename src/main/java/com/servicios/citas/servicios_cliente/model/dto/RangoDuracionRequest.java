package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class RangoDuracionRequest {
    @NotNull(message = "Duración no puede ser nula")
    private LocalTime duracion;
}
