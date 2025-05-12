package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.BloqueDisponibleRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.BloqueDisponible;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.service.BloqueDisponibleServicio;
import com.servicios.citas.servicios_cliente.service.ServicioServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bloques-disponibles")
public class BloqueDisponibleController {
    private final BloqueDisponibleServicio service;

    public BloqueDisponibleController(BloqueDisponibleServicio service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<BloqueDisponible>> all() {
        return ResponseEntity.ok(service.listAll());
    }
    /*
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    @PostMapping
    public ResponseEntity<BloqueDisponible> create(@RequestBody @Valid BloqueDisponibleRequest req) {
        BloqueDisponible saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloqueDisponible> update(
            @PathVariable Long id,
            @RequestBody @Valid BloqueDisponibleRequest req
    ) {
        BloqueDisponible updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
