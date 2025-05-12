package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.SedeServicioRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.*;
import com.servicios.citas.servicios_cliente.model.entity.SedeServicio;
import com.servicios.citas.servicios_cliente.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SedeServicioServicio {
    private final SedeServicioRepository repo;
    private final SedeRepository sedeRepo;
    private final ServicioRepository servicioRepo;

    public SedeServicioServicio(
            SedeServicioRepository repo,
            SedeRepository sedeRepo,
            ServicioRepository servicioRepo
    ) {
        this.repo = repo;
        this.sedeRepo = sedeRepo;
        this.servicioRepo = servicioRepo;
    }

    public List<SedeServicio> listAll() {
        return repo.findAll();
    }

    public Optional<SedeServicio> get(Long id) {
        return repo.findById(id);
    }

    public SedeServicio create(SedeServicioRequest req) {
        Sede rd = sedeRepo.findById(req.getId_sede())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sede id no existe"));
        Servicio ct = servicioRepo.findById(req.getId_servicio())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Servicio id no existe"));
        SedeServicio s = mapRequestToEntity(new SedeServicio(), rd,ct);
        return repo.save(s);
    }


    public void delete(Long id) {
        SedeServicio c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Rompe la asociación
        if (c.getServicio() != null) {
            c.setServicio(null);      // rompes el FK en Servicio
        }
        if (c.getSede() != null) {
            c.setSede(null);  // idem
        }
        repo.delete(c);
    }

    private SedeServicio mapRequestToEntity(
            SedeServicio c,
            Sede s, Servicio se
    ) {


        c.setServicio(se);
        c.setSede(s);
        return c;
    }
}
