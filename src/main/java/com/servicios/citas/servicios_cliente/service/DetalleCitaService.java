package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.DetalleCitaRequest;
import com.servicios.citas.servicios_cliente.model.entity.*;
import com.servicios.citas.servicios_cliente.repository.BloqueDisponibleRepository;
import com.servicios.citas.servicios_cliente.repository.CitaRepository;
import com.servicios.citas.servicios_cliente.repository.DetalleCitaRepository;
import com.servicios.citas.servicios_cliente.repository.TerapeutaSedeServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DetalleCitaService {
    private final DetalleCitaRepository repo;
    private final CitaRepository citaRepo;

    private final TerapeutaSedeServicioRepository tRepo;
    private final BloqueDisponibleRepository bloqueRepo;

    public DetalleCitaService(
            DetalleCitaRepository repo,
            CitaRepository citaRepo,
            TerapeutaSedeServicioRepository tRepo,
            BloqueDisponibleRepository bloqueRepo
    ) {
        this.repo = repo;
        this.citaRepo = citaRepo;
        this.tRepo = tRepo;
        this.bloqueRepo = bloqueRepo;
    }

    public List<DetalleCita> list(Long citaId) {
        citaRepo.findById(citaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no existe"));
        return repo.findByCitaId(citaId);
    }

    public DetalleCita add(Long citaId, DetalleCitaRequest req) {
        Cita cita = citaRepo.findById(citaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no existe"));

        TerapeutaSedeServicio ter = tRepo.findById(req.getId_terapeuta_sede_servicio())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TerapeutaSedeServicio no existe"));
        BloqueDisponible bd = bloqueRepo.findById(req.getId_bloque_disponible())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bloque no existe"));

        DetalleCita d = new DetalleCita();
        d.setCita(cita);
        d.setTerapeutaSedeServicio(ter);
        d.setSub_monto(req.getSub_monto());
        d.setBloqueDisponible(bd);

        return repo.save(d);
    }

    @Transactional
    public void remove(Long citaId, Long detId) {
        DetalleCita d = repo.findById(detId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (d.getCita() == null || !Objects.equals(d.getCita().getId(), citaId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Detalle no pertenece a esa cita");
        }

        repo.delete(d);
    }
}
