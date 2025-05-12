package com.servicios.citas.servicios_cliente.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicioRequest {
    @NotEmpty(message = "Nombre vacio")
    private String descripcion;
    @NotEmpty(message = "Nombre vacio")
    private String nombre;
    @NotNull(message = "Precio vacio")
    private BigDecimal precio;
    @NotNull(message = "Categoria vacio")
    private  long id_categoria_terapia;
    @NotNull(message = "Duracion vacio")
    private  long id_rango_duracion;
}
