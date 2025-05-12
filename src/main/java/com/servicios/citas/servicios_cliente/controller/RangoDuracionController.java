package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.RangoDuracionRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;
import com.servicios.citas.servicios_cliente.model.entity.RangoDuracion;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.RangoDuracionServicio;
import com.servicios.citas.servicios_cliente.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rangos-duracion")
public class RangoDuracionController {

    private final RangoDuracionServicio service;

    public RangoDuracionController(RangoDuracionServicio service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<RangoDuracion>> all() {
        return ResponseEntity.ok(service.listAll());
    }



    @PostMapping
    public ResponseEntity<RangoDuracion> create(@RequestBody @Valid RangoDuracionRequest req) {
        RangoDuracion saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RangoDuracion> update(
            @PathVariable Long id,
            @RequestBody @Valid RangoDuracionRequest req
    ) {
        RangoDuracion updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
