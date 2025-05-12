package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.BloqueDisponibleRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.BloqueDisponible;
import com.servicios.citas.servicios_cliente.model.entity.CategoriaTerapia;
import com.servicios.citas.servicios_cliente.model.entity.DetalleCita;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.service.BloqueDisponibleServicio;
import com.servicios.citas.servicios_cliente.service.ServicioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Bloques", description = "Gestión de bloques")
@RestController
@RequestMapping("/api/bloques-disponibles")
public class BloqueDisponibleController {
    private final BloqueDisponibleServicio service;

    public BloqueDisponibleController(BloqueDisponibleServicio service) {
        this.service = service;
    }
    @Operation(
            summary = "Listar todos los bloques ",
            description = "Devuelve la lista completa de bloques  registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BloqueDisponible.class)))
    @GetMapping
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

    @Operation(summary = "Crear un nuevo bloque ",
            description = "Registra un bloque ")
    @ApiResponse(responseCode = "201", description = "Bloque creado",
            content = @Content(schema = @Schema(implementation = BloqueDisponible.class)))
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

    @Operation(summary = "Actualizar un bloque existente")
    @ApiResponse(responseCode = "200", description = "bloque actualizado",
            content = @Content(schema = @Schema(implementation = BloqueDisponible.class)))
    @PutMapping("/{id}")
    public ResponseEntity<BloqueDisponible> update(
            @PathVariable Long id,
            @RequestBody @Valid BloqueDisponibleRequest req
    ) {
        BloqueDisponible updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un bloque por ID ")
    @ApiResponse(responseCode = "204", description = "bloque eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
