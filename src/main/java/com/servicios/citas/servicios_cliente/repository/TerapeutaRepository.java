package com.servicios.citas.servicios_cliente.repository;

import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
}
