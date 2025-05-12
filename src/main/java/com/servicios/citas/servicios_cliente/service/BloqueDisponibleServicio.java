package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.BloqueDisponibleRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.BloqueDisponible;
import com.servicios.citas.servicios_cliente.model.entity.CategoriaTerapia;
import com.servicios.citas.servicios_cliente.model.entity.RangoDuracion;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.repository.BloqueDisponibleRepository;
import com.servicios.citas.servicios_cliente.repository.CategoriaTerapiaRepository;
import com.servicios.citas.servicios_cliente.repository.RangoDuracionRepository;
import com.servicios.citas.servicios_cliente.repository.ServicioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BloqueDisponibleServicio {
    private final BloqueDisponibleRepository repo;
    private final RangoDuracionRepository duracionRepo;

    public BloqueDisponibleServicio(
            BloqueDisponibleRepository repo,
            RangoDuracionRepository duracionRepo
    ) {
        this.repo = repo;
        this.duracionRepo = duracionRepo;
    }

    public List<BloqueDisponible> listAll() {
        return repo.findAll();
    }



    public BloqueDisponible create(BloqueDisponibleRequest req) {
        RangoDuracion rd = duracionRepo.findById(req.getId_rango_duracion())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duracion id no existe"));

        BloqueDisponible s = mapRequestToEntity(new BloqueDisponible(), req, rd);
        return repo.save(s);
    }

    public BloqueDisponible update(Long id, BloqueDisponibleRequest req) {
        RangoDuracion rd = duracionRepo.findById(req.getId_rango_duracion())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duracion id no existe"));

        BloqueDisponible existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Bloque no existe"));
        existing = mapRequestToEntity(existing, req, rd);
        return repo.save(existing);
    }

    public void delete(Long id) {
        BloqueDisponible c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Rompe la asociación

        if (c.getRango_duracion() != null) {
            c.setRango_duracion(null);  // idem
        }
        repo.delete(c);
    }

    private BloqueDisponible mapRequestToEntity(
            BloqueDisponible c, BloqueDisponibleRequest req,
            RangoDuracion rd
    ) {
        c.setHora_fin(req.getHora_fin());
        c.setHora_inicio(req.getHora_inicio());

        c.setRango_duracion(rd);
        return c;
    }
}
