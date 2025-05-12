package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.RangoDuracionRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;
import com.servicios.citas.servicios_cliente.model.entity.RangoDuracion;
import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.RangoDuracionServicio;
import com.servicios.citas.servicios_cliente.service.UsuarioService;
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

@Tag(name = "Duracion", description = "Gestión de Rango de duracion")
@RestController
@RequestMapping("/api/rangos-duracion")
public class RangoDuracionController {

    private final RangoDuracionServicio service;

    public RangoDuracionController(RangoDuracionServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los Duracion ",
            description = "Devuelve la lista completa de duracion  registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RangoDuracion.class)))
    @GetMapping
    public ResponseEntity<List<RangoDuracion>> all() {
        return ResponseEntity.ok(service.listAll());
    }


    @Operation(summary = "Crear un nuevo duracion",
            description = "Registra un duracion con duracion")
    @ApiResponse(responseCode = "201", description = "RangoDuracion creado",
            content = @Content(schema = @Schema(implementation = RangoDuracion.class)))
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

    @Operation(summary = "Actualizar un Duracion existente")
    @ApiResponse(responseCode = "200", description = "Duracion actualizado",
            content = @Content(schema = @Schema(implementation = RangoDuracion.class)))
    @PutMapping("/{id}")
    public ResponseEntity<RangoDuracion> update(
            @PathVariable Long id,
            @RequestBody @Valid RangoDuracionRequest req
    ) {
        RangoDuracion updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un Duracion por ID")
    @ApiResponse(responseCode = "204", description = "Duracion eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
