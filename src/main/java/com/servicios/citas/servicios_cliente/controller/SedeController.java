package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.SedeRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;

import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.SedeServicio;
import com.servicios.citas.servicios_cliente.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    private final SedeServicio service;

    public SedeController(SedeServicio service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Sede>> all() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sede> create(@RequestBody @Valid SedeRequest req) {
        Sede saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sede> update(
            @PathVariable Long id,
            @RequestBody @Valid SedeRequest req
    ) {
        Sede updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
