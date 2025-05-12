package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Cliente-Request", description = "Datos para crear o actualizar un cliente")
public class ClienteRequest {
    @NotEmpty(message = "Nombre vacio")
    @Schema(description = "Digitar un nombre", example = "Antony", required = true)
    private String nombre;

    @NotEmpty(message = "Apellido vacio")
    @Schema(description = "Digitar un apellido", example = "Aranda", required = true)
    private String apellido;

    @NotEmpty(message = "Dni vacio")
    @Schema(description = "Digitar un dni", example = "72372312", required = true)
    private String dni;

    @NotEmpty(message = "Telefono vacio")
    @Schema(description = "Digitar un telefono", example = "9801281212", required = true)
    private String telefono;

    @NotEmpty(message = "Genero vacio")
    @Schema(description = "Especificar un genero (F - M )", example = "M", required = true)
    private String genero;

    @NotNull(message = "Estado vacio")
    @Schema(description = "Definir un estado de la cuenta", example = "0", required = true)
    private int estado;

    @NotNull(message = "Usuario no elegido")
    @Schema(description = "Especificar el usuario para el cliente", example = "1", required = true)
    private Long id_usuario;        // <-- solo el ID del usuario
    // getters/setters...
}
