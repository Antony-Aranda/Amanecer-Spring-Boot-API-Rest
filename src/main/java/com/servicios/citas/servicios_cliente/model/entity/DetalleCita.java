package com.servicios.citas.servicios_cliente.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "detalle_cita")
public class DetalleCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private BigDecimal sub_monto;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    @JsonBackReference
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "id_terapeuta_sede_servicio")
    private TerapeutaSedeServicio terapeutaSedeServicio;

    @ManyToOne
    @JoinColumn(name = "id_bloque_disponible")
    private BloqueDisponible bloqueDisponible;
}
