package com.servicios.citas.servicios_cliente.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String descripcion;
    private String nombre;
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "id_categoria_terapia")
    private CategoriaTerapia categoria;

    @ManyToOne
    @JoinColumn(name = "id_rango_duracion")
    private RangoDuracion rangoDuracion;
}
