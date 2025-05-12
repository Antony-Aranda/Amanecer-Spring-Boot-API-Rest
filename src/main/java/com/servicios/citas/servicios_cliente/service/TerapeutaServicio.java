package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.TerapeutaRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;
import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.repository.TerapeutaRepository;
import com.servicios.citas.servicios_cliente.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TerapeutaServicio {
    private final TerapeutaRepository repo;


    public TerapeutaServicio(TerapeutaRepository repo ) {
        this.repo = repo;

    }

    public List<Terapeuta> listAll() {
        return repo.findAll();
    }

    public Optional<Terapeuta> get(Long id) {
        return repo.findById(id);
    }

    public Terapeuta create(TerapeutaRequest req) {
        /*Usuario u = usuarioRepo.findById(req.getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));*/
        Terapeuta u = mapRequestToEntity(new Terapeuta(), req);
        return repo.save(u);
    }

    public Terapeuta update(Long id, TerapeutaRequest req) {
        Terapeuta existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Terapeuta no existe"));

        existing = mapRequestToEntity(existing, req);
        return repo.save(existing);
    }

    public void delete(Long id) {
        Terapeuta c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repo.delete(c);
    }

    private Terapeuta mapRequestToEntity(Terapeuta u, TerapeutaRequest req) {
        u.setApellido(req.getApellido());
        u.setNombre(req.getNombre());
        u.setDni(req.getDni());
        return u;
    }
}
