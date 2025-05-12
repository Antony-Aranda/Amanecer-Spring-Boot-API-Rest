package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.CitaRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.*;
import com.servicios.citas.servicios_cliente.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServicio {
    private final CitaRepository repo;
    private final ClienteRepository cRepo;


    public CitaServicio(
            CitaRepository repo,
           ClienteRepository cRepo
    ) {
        this.repo = repo;
        this.cRepo = cRepo;

    }

    public List<Cita> listAll() {
        return repo.findAll();
    }

        public Optional<Cita> get(Long id) {
        return repo.findById(id);
    }

    public Cita create(CitaRequest req) {
        Cliente rd = cRepo.findById(req.getId_cliente())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente id no existe"));

        Cita s = mapRequestToEntity(new Cita(), req, rd);
        return repo.save(s);
    }

    public Cita update(Long id, CitaRequest req) {
        Cliente rd = cRepo.findById(req.getId_cliente())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente id no existe"));

        Cita existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cita no existe"));
        existing = mapRequestToEntity(existing, req, rd);
        return repo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        Cita c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (c.getDetalleCita() != null && !c.getDetalleCita().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar la cita porque tiene detalles");
        }
        repo.delete(c);
        // Aquí, si repo.delete lanza alguna excepción, todo se revierte
    }

    private Cita mapRequestToEntity(
            Cita c, CitaRequest req,
            Cliente rd
    ) {
        c.setEstado(req.getEstado());
        c.setDuracion_total(req.getDuracion_total());
        c.setTotal(req.getTotal());
        c.setFecha_reserva(req.getFecha_reserva());

        c.setCliente(rd);
        return c;
    }
}
