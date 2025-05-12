package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.CategoriaTerapiaRequest;
import com.servicios.citas.servicios_cliente.model.dto.SedeRequest;
import com.servicios.citas.servicios_cliente.model.entity.CategoriaTerapia;
import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.repository.CategoriaTerapiaRepository;
import com.servicios.citas.servicios_cliente.repository.SedeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaTerapiaServicio {
    private final CategoriaTerapiaRepository repo;


    public CategoriaTerapiaServicio(CategoriaTerapiaRepository repo ) {
        this.repo = repo;

    }

    public List<CategoriaTerapia> listAll() {
        return repo.findAll();
    }

    public Optional<CategoriaTerapia> get(Long id) {
        return repo.findById(id);
    }

    public CategoriaTerapia create(CategoriaTerapiaRequest req) {
        /*Usuario u = usuarioRepo.findById(req.getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));*/
        CategoriaTerapia u = mapRequestToEntity(new CategoriaTerapia(), req);
        return repo.save(u);
    }

    public CategoriaTerapia update(Long id, CategoriaTerapiaRequest req) {
        CategoriaTerapia existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria no existe"));

        existing = mapRequestToEntity(existing, req);
        return repo.save(existing);
    }

    public void delete(Long id) {
        CategoriaTerapia c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repo.delete(c);
    }

    private CategoriaTerapia mapRequestToEntity(CategoriaTerapia u, CategoriaTerapiaRequest req) {
        u.setNombre(req.getNombre());
        u.setDescripcion(req.getDescripcion());
        return u;
    }
}
