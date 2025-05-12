package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Schema(name = "Cita-Request", description = "Datos para crear o actualizar una bloque")
public class BloqueDisponibleRequest {

    @NotNull(message = "Hora de inicio vacio")
    @Schema(description = "Definir hora de inicio", example = "07:00:00", required = true)
    private LocalTime hora_inicio;

    @NotNull(message = "Hora de cierre vacio")
    @Schema(description = "Definir hora de final", example = "08:00:00", required = true)
    private LocalTime hora_fin;

    @NotNull(message = "Duracion vacio")
    @Schema(description = "Seleccionar un rago de duracion", example = "1", required = true)
    private long id_rango_duracion;
}
