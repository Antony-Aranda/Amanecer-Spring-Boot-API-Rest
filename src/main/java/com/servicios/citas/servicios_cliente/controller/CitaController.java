package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.CitaRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.Cita;
import com.servicios.citas.servicios_cliente.model.entity.Cliente;
import com.servicios.citas.servicios_cliente.model.entity.DetalleCita;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.service.CitaServicio;
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

@Tag(name = "Citas", description = "Gestión de citas")
@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {
    private final CitaServicio service;

    public CitaController(CitaServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos las cita ",
            description = "Devuelve la lista completa de  citas  registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cita.class)))
    @GetMapping
    public ResponseEntity<List<Cita>> all() {
        return ResponseEntity.ok(service.listAll());
    }

    @Operation(summary = "Obtener un cita por ID",
            description = "Busca un cita existente usando su identificador")
    @ApiResponse(responseCode = "200", description = "cita encontrado",
            content = @Content(schema = @Schema(implementation = Cita.class)))
    @ApiResponse(responseCode = "404", description = "cita no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Crear un nuevo cita ",
            description = "Registra un cita con fecha,total, id cliente, etc")
    @ApiResponse(responseCode = "201", description = "Cita creado",
            content = @Content(schema = @Schema(implementation = Cita.class)))
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

    @Operation(summary = "Actualizar un Cita existente")
    @ApiResponse(responseCode = "200", description = "Cita actualizado",
            content = @Content(schema = @Schema(implementation = Cita.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(
            @PathVariable Long id,
            @RequestBody @Valid CitaRequest req
    ) {
        Cita updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un Cita por ID ")
    @ApiResponse(responseCode = "204", description = "Cita eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
