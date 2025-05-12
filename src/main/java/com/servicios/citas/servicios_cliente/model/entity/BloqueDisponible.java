package com.servicios.citas.servicios_cliente.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "Bloque_disponible")
public class BloqueDisponible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private LocalTime hora_inicio;
    private LocalTime hora_fin;

    @ManyToOne
    @JoinColumn(name = "id_rango_duracion")
    private RangoDuracion rango_duracion;
}
