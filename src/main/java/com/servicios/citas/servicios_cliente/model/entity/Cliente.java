package com.servicios.citas.servicios_cliente.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String genero;
    private int estado;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;
}
