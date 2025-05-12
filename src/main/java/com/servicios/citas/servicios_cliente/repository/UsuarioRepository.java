package com.servicios.citas.servicios_cliente.repository;

import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
