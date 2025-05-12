package com.servicios.citas.servicios_cliente.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "terapeuta_sede_servicio")
public class TerapeutaSedeServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @ManyToOne
    @JoinColumn(name = "id_terapeuta")
    private Terapeuta terapeuta;

    @ManyToOne
    @JoinColumn(name = "id_sede_servicio")
    private SedeServicio sedeServicio;
}
