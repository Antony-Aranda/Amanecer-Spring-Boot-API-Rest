package com.servicios.citas.servicios_cliente.repository;

import com.servicios.citas.servicios_cliente.model.entity.BloqueDisponible;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueDisponibleRepository extends JpaRepository<BloqueDisponible, Long> {
}
