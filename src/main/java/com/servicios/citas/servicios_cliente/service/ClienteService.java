package com.servicios.citas.servicios_cliente.service;

import com.servicios.citas.servicios_cliente.model.entity.Cliente;
import com.servicios.citas.servicios_cliente.model.dto.ClienteRequest;
import com.servicios.citas.servicios_cliente.repository.ClienteRepository;
import com.servicios.citas.servicios_cliente.repository.UsuarioRepository;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repo;
    private final UsuarioRepository usuarioRepo;

    public ClienteService(ClienteRepository repo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Cliente> listAll() {
        return repo.findAll();
    }

    public Optional<Cliente> get(Long id) {
        return repo.findById(id);
    }

    public Cliente create(ClienteRequest req) {
        Usuario u = usuarioRepo.findById(req.getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));
        Cliente c = mapRequestToEntity(new Cliente(), req, u);
        return repo.save(c);
    }

    public Cliente update(Long id, ClienteRequest req) {
        Usuario u = usuarioRepo.findById(req.getId_usuario())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no existe"));
        Cliente existing = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no existe"));
        existing = mapRequestToEntity(existing, req, u);
        return repo.save(existing);
    }

    public void delete(Long id) {
        Cliente c = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Rompe la asociación
        if (c.getUsuario() != null) {
            c.getUsuario().setCliente(null);
        }
        repo.delete(c);
    }

    private Cliente mapRequestToEntity(Cliente c, ClienteRequest req, Usuario u) {
        c.setNombre(req.getNombre());
        c.setApellido(req.getApellido());
        c.setDni(req.getDni());
        c.setTelefono(req.getTelefono());
        c.setGenero(req.getGenero());
        c.setEstado(req.getEstado());
        c.setUsuario(u);
        return c;
    }
}
