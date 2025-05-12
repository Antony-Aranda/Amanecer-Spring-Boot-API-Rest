package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.SedeRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;
import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.repository.SedeRepository;
import com.servicios.citas.servicios_cliente.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SedeServicio {
    private final SedeRepository repo;


    public SedeServicio(SedeRepository repo ) {
        this.repo = repo;

    }

    public List<Sede> listAll() {
        return repo.findAll();
    }

    public Optional<Sede> get(Long id) {
        return repo.findById(id);
    }

    public Sede create(SedeRequest req) {
        /*Usuario u = usuarioRepo.findById(req.getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));*/
        Sede u = mapRequestToEntity(new Sede(), req);
        return repo.save(u);
    }

    public Sede update(Long id, SedeRequest req) {
        Sede existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sede no existe"));

        existing = mapRequestToEntity(existing, req);
        return repo.save(existing);
    }

    public void delete(Long id) {
        Sede c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repo.delete(c);
    }

    private Sede mapRequestToEntity(Sede u, SedeRequest req) {
        u.setNombre(req.getNombre());
        u.setDistrito(req.getDistrito());
        u.setDepartamento(req.getDepartamento());
        u.setDireccion(req.getDireccion());
        u.setHora_cerrado(req.getHora_cerrado());
        u.setHora_abierto(req.getHora_abierto());
        return u;
    }
}
