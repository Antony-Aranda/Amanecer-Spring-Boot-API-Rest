package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import com.servicios.citas.servicios_cliente.model.entity.TerapeutaSedeServicio;
import com.servicios.citas.servicios_cliente.service.TerapeutaSedeServicioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sedes-servicios/{ssId}/terapeutas")
public class TerapeutaSedeServicioController {
    private final TerapeutaSedeServicioServicio service;

    public TerapeutaSedeServicioController(TerapeutaSedeServicioServicio service) {
        this.service = service;
    }

    @GetMapping
    public List<Terapeuta> list(@PathVariable Long ssId) {
        return service.listByAsignacion(ssId);
    }

    @PostMapping
    public ResponseEntity<Void> add(@PathVariable Long ssId,
                                    @RequestBody Map<String,Long> body) {
        Long terapeutaId = body.get("terapeutaId");
        TerapeutaSedeServicio created = service.add(ssId, terapeutaId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{terapeutaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long ssId,
                       @PathVariable Long terapeutaId) {
        service.remove(ssId, terapeutaId);
    }
}
