package com.servicios.citas.servicios_cliente.repository;

import com.servicios.citas.servicios_cliente.model.entity.TerapeutaSedeServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerapeutaSedeServicioRepository extends JpaRepository<TerapeutaSedeServicio, Long> {
    List<TerapeutaSedeServicio> findBySedeServicioId(Long ssId);
    void deleteBySedeServicioIdAndTerapeutaId(Long ssId, Long terapeutaId);
}
