package com.servicios.citas.servicios_cliente.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "terapeuta")
public class Terapeuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private  String nombre;
    private String apellido;
    private String dni;
}
