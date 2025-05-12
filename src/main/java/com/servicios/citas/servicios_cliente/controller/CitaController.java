package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.CitaRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.Cita;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.service.CitaServicio;
import com.servicios.citas.servicios_cliente.service.ServicioServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    private final CitaServicio service;

    public CitaController(CitaServicio service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Cita>> all() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody @Valid CitaRequest req) {
        Cita saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(
            @PathVariable Long id,
            @RequestBody @Valid CitaRequest req
    ) {
        Cita updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
