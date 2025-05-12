package com.servicios.citas.servicios_cliente.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categoria_terapia")
public class CategoriaTerapia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String nombre;
    private String descripcion;
}
