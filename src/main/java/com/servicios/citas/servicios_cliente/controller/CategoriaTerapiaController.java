package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.CategoriaTerapiaRequest;
import com.servicios.citas.servicios_cliente.model.dto.SedeRequest;
import com.servicios.citas.servicios_cliente.model.entity.CategoriaTerapia;
import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.service.CategoriaTerapiaServicio;
import com.servicios.citas.servicios_cliente.service.SedeServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias-terapia")
public class CategoriaTerapiaController {
    private final CategoriaTerapiaServicio service;

    public CategoriaTerapiaController(CategoriaTerapiaServicio service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<CategoriaTerapia>> all() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaTerapia> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaTerapia> create(@RequestBody @Valid CategoriaTerapiaRequest req) {
        CategoriaTerapia saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaTerapia> update(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaTerapiaRequest req
    ) {
        CategoriaTerapia updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
