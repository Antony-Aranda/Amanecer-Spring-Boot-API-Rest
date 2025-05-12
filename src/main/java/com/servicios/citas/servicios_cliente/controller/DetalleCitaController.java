package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.DetalleCitaRequest;
import com.servicios.citas.servicios_cliente.model.entity.DetalleCita;
import com.servicios.citas.servicios_cliente.service.DetalleCitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/citas/{citaId}/detalles")
public class DetalleCitaController {
    private final DetalleCitaService service;

    public DetalleCitaController(DetalleCitaService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetalleCita> list(@PathVariable Long citaId) {
        return service.list(citaId);
    }

    @PostMapping
    public ResponseEntity<DetalleCita> add(
            @PathVariable Long citaId,
            @RequestBody @Valid DetalleCitaRequest req) {

        DetalleCita created = service.add(citaId, req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/{detId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(
            @PathVariable Long citaId,
            @PathVariable Long detId) {
        service.remove(citaId, detId);
    }
}
