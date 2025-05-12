package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "TerapeutaSedeServicioRequest", description = "Datos para crear o actualizar un Terapeuta o asignar con una sede o servicio")
public class TerapeutaSedeServicioRequest {
    @NotNull(message = "Terapeuta vacia")
    @Schema(description = "Id Terapeuta a asignar", example = "1", required = true)
    private long id_terapeuta;

}
