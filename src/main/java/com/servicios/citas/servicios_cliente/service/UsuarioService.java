package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.dto.ClienteRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;
import com.servicios.citas.servicios_cliente.model.entity.Cliente;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.repository.ClienteRepository;
import com.servicios.citas.servicios_cliente.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;


    public UsuarioService(UsuarioRepository repo ) {
        this.repo = repo;

    }

    public List<Usuario> listAll() {
        return repo.findAll();
    }

    public Optional<Usuario> get(Long id) {
        return repo.findById(id);
    }

    public Usuario create(UsuarioRequest req) {
        /*Usuario u = usuarioRepo.findById(req.getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));*/
        Usuario u = mapRequestToEntity(new Usuario(), req);
        return repo.save(u);
    }

    public Usuario update(Long id, UsuarioRequest req) {
        Usuario existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));

        existing = mapRequestToEntity(existing, req);
        return repo.save(existing);
    }

    public void delete(Long id) {
        Usuario c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repo.delete(c);
    }

    private Usuario mapRequestToEntity(Usuario u, UsuarioRequest req) {
        u.setCorreo(req.getCorreo());
        u.setPassword(req.getPassword());
        u.setEstado(req.getEstado());
        u.setNombre(req.getNombre());
        u.setRol(req.getRol());
        return u;
    }
}
