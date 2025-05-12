package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.entity.SedeServicio;
import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import com.servicios.citas.servicios_cliente.model.entity.TerapeutaSedeServicio;
import com.servicios.citas.servicios_cliente.repository.SedeServicioRepository;
import com.servicios.citas.servicios_cliente.repository.TerapeutaRepository;
import com.servicios.citas.servicios_cliente.repository.TerapeutaSedeServicioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TerapeutaSedeServicioServicio {
    private final TerapeutaSedeServicioRepository repo;
    private final SedeServicioRepository ssRepo;
    private final TerapeutaRepository tRepo;

    public TerapeutaSedeServicioServicio(TerapeutaSedeServicioRepository repo,
                                      SedeServicioRepository ssRepo,
                                      TerapeutaRepository tRepo) {
        this.repo = repo; this.ssRepo = ssRepo; this.tRepo = tRepo;
    }

    public List<Terapeuta> listByAsignacion(Long ssId) {
        return repo.findBySedeServicioId(ssId)
                .stream()
                .map(TerapeutaSedeServicio::getTerapeuta)
                .toList();
    }

    public TerapeutaSedeServicio add(Long ssId, Long terapeutaId) {
        SedeServicio ss = ssRepo.findById(ssId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Terapeuta t = tRepo.findById(terapeutaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TerapeutaSedeServicio asig = new TerapeutaSedeServicio();
        asig.setSedeServicio(ss);
        asig.setTerapeuta(t);
        return repo.save(asig);
    }

    public void remove(Long ssId, Long terapeutaId) {
        repo.deleteBySedeServicioIdAndTerapeutaId(ssId, terapeutaId);
    }
}
