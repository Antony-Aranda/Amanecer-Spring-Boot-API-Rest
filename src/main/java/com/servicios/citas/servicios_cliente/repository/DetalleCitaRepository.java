package com.servicios.citas.servicios_cliente.repository;

import com.servicios.citas.servicios_cliente.model.entity.DetalleCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleCitaRepository extends JpaRepository<DetalleCita, Long> {
    List<DetalleCita> findByCitaId(Long citaId);
    void deleteByCitaIdAndId(Long citaId, Long detId);
}
