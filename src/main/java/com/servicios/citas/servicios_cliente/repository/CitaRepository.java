package com.servicios.citas.servicios_cliente.repository;

import com.servicios.citas.servicios_cliente.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}
