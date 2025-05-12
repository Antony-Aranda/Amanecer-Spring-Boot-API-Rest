package com.servicios.citas.servicios_cliente.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "rango_duracion")
public class RangoDuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private LocalTime duracion;
}
