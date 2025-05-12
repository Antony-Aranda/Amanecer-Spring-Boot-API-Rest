package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class SedeRequest {
    @NotEmpty(message = "Nombre vacio")
    private  String nombre;
    @NotEmpty(message = "direccion vacio")
    private  String direccion;
    @NotEmpty(message = "distrito vacio")
    private  String distrito;
    @NotEmpty(message = "departamento vacio")
    private  String departamento;
    @NotNull(message = "hora_abierto vacio")
    private LocalTime hora_abierto;
    @NotNull(message = "hora_cerrado vacio")
    private  LocalTime hora_cerrado;
}
