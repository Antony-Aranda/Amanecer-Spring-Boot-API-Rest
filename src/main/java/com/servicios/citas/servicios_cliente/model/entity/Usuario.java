package com.servicios.citas.servicios_cliente.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String correo;
    private String password;
    private int estado;
    private  String nombre;
    private  String rol;

    @OneToOne(mappedBy = "usuario")
    @JsonBackReference
    private Cliente cliente;

}
