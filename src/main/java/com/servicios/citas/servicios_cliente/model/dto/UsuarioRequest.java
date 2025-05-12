package com.servicios.citas.servicios_cliente.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Schema(name = "UsuarioRequest", description = "Datos para crear o actualizar un usuario")  // Describe el modelo :contentReference[oaicite:3]{index=3}
public class UsuarioRequest {

    @NotEmpty(message = "Correo vacío")
    @Schema(description = "Correo electrónico del usuario", example = "luna@gmail.com", required = true)
    private String correo;

    @NotEmpty(message = "Contraseña vacía")
    @Schema(description = "Contraseña de acceso", example = "P@ssw0rd", required = true)
    private String password;

    @NotNull(message = "Estado vacío")
    @Schema(description = "Estado del usuario (1=activo,0=inactivo)", example = "1", required = true)
    private Integer estado;

    @NotEmpty(message = "Nombre vacío")
    @Schema(description = "Nombre completo del usuario", example = "Luna", required = true)
    private String nombre;

    @NotEmpty(message = "Rol vacío")
    @Schema(description = "Rol asignado al usuario", example = "admin", required = true)
    private String rol;
}
