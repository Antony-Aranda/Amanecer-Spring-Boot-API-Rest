package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.ClienteRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.*;
import com.servicios.citas.servicios_cliente.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServicio {
    private final ServicioRepository repo;
    private final CategoriaTerapiaRepository categoriaRepo;
    private final RangoDuracionRepository duracionRepo;

    public ServicioServicio(
            ServicioRepository repo,
            CategoriaTerapiaRepository categoriaRepo,
            RangoDuracionRepository duracionRepo
    ) {
        this.repo = repo;
        this.categoriaRepo = categoriaRepo;
        this.duracionRepo = duracionRepo;
    }

    public List<Servicio> listAll() {
        return repo.findAll();
    }

    public Optional<Servicio> get(Long id) {
        return repo.findById(id);
    }

    public Servicio create(ServicioRequest req) {
        RangoDuracion rd = duracionRepo.findById(req.getId_rango_duracion())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duracion id no existe"));
        CategoriaTerapia ct = categoriaRepo.findById(req.getId_categoria_terapia())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria id no existe"));
        Servicio s = mapRequestToEntity(new Servicio(), req, rd,ct);
        return repo.save(s);
    }

    public Servicio update(Long id, ServicioRequest req) {
        RangoDuracion rd = duracionRepo.findById(req.getId_rango_duracion())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duracion id no existe"));
        CategoriaTerapia ct = categoriaRepo.findById(req.getId_categoria_terapia())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria id no existe"));
        Servicio existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Servicio no existe"));
        existing = mapRequestToEntity(existing, req, rd,ct);
        return repo.save(existing);
    }

    public void delete(Long id) {
        Servicio c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Rompe la asociación
        if (c.getCategoria() != null) {
            c.setCategoria(null);      // rompes el FK en Servicio
        }
        if (c.getRangoDuracion() != null) {
            c.setRangoDuracion(null);  // idem
        }
        repo.delete(c);
    }

    private Servicio mapRequestToEntity(
            Servicio c, ServicioRequest req,
            RangoDuracion rd, CategoriaTerapia ct
    ) {
        c.setNombre(req.getNombre());
        c.setPrecio(req.getPrecio());
        c.setDescripcion(req.getDescripcion());

        c.setRangoDuracion(rd);
        c.setCategoria(ct);
        return c;
    }

}
