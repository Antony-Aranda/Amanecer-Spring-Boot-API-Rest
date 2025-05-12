package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CategoriaTerapia-Request", description = "Datos para crear o actualizar una categoria")
public class CategoriaTerapiaRequest {
    @NotEmpty(message = "Nombre vacio")
    @Schema(description = "Digitar el nombre de la categoria", example = "Escolares", required = true)
    private String nombre;

    @NotEmpty(message = "Descripcion vacio")
    @Schema(description = "Detallar una descripcion", example = "doskdosdkosdkds....", required = true)
    private String descripcion;
}
