package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "Servicio-Request", description = "Datos para crear o actualizar un Servicio")
public class ServicioRequest {
    @NotEmpty(message = "Nombre vacio")
    @Schema(description = "descripcion del servicio", example = "rojo ,verder grnade", required = true)
    private String descripcion;

    @NotEmpty(message = "Nombre vacio")
    @Schema(description = "Nombre del servicio", example = "Carnales", required = true)
    private String nombre;

    @NotNull(message = "Precio vacio")
    @Schema(description = "Precio del servicio", example = "912.1", required = true)
    private BigDecimal precio;

    @NotNull(message = "Categoria vacio")
    @Schema(description = "Selecciona un acategoria", example = "1", required = true)
    private  long id_categoria_terapia;

    @NotNull(message = "Duracion vacio")
    @Schema(description = "Selecciona una duracion", example = "1", required = true)
    private  long id_rango_duracion;
}
