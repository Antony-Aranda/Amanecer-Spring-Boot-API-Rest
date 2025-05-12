package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class BloqueDisponibleRequest {
    @NotNull(message = "Hora de inicio vacio")
    private LocalTime hora_inicio;
    @NotNull(message = "Hora de cierre vacio")
    private LocalTime hora_fin;
    @NotNull(message = "Duracion vacio")
    private long id_rango_duracion;
}
