package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.RangoDuracionRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;
import com.servicios.citas.servicios_cliente.model.entity.RangoDuracion;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.repository.RangoDuracionRepository;
import com.servicios.citas.servicios_cliente.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RangoDuracionServicio {
    private final RangoDuracionRepository repo;


    public RangoDuracionServicio(RangoDuracionRepository repo ) {
        this.repo = repo;

    }

    public List<RangoDuracion> listAll() {
        return repo.findAll();
    }



    public RangoDuracion create(RangoDuracionRequest req) {
        /*Usuario u = usuarioRepo.findById(req.getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));*/
        RangoDuracion u = mapRequestToEntity(new RangoDuracion(), req);
        return repo.save(u);
    }

    public RangoDuracion update(Long id, RangoDuracionRequest req) {
        RangoDuracion existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duracion no existe"));

        existing = mapRequestToEntity(existing, req);
        return repo.save(existing);
    }

    public void delete(Long id) {
        RangoDuracion c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repo.delete(c);
    }

    private RangoDuracion mapRequestToEntity(RangoDuracion u, RangoDuracionRequest req) {
        u.setDuracion(req.getDuracion());

        return u;
    }
}
