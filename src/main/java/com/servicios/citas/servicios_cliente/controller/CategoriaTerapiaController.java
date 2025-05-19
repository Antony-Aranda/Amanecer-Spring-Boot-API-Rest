package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.CategoriaTerapiaRequest;
import com.servicios.citas.servicios_cliente.model.dto.SedeRequest;
import com.servicios.citas.servicios_cliente.model.entity.CategoriaTerapia;
import com.servicios.citas.servicios_cliente.model.entity.Cita;
import com.servicios.citas.servicios_cliente.model.entity.DetalleCita;
import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.service.CategoriaTerapiaServicio;
import com.servicios.citas.servicios_cliente.service.SedeServicio;
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

@Tag(name = "Categoria", description = "Gestión de categorias")
@RestController
@RequestMapping("/api/categorias-terapia")
@CrossOrigin(origins = "*")
public class CategoriaTerapiaController {
    private final CategoriaTerapiaServicio service;

    public CategoriaTerapiaController(CategoriaTerapiaServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos las categorias ",
            description = "Devuelve la lista completa de categoria  registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoriaTerapia.class)))
    @GetMapping
    public ResponseEntity<List<CategoriaTerapia>> all() {
        return ResponseEntity.ok(service.listAll());
    }


    @Operation(summary = "Obtener una categoria por ID",
            description = "Busca una categoria existente usando su identificador")
    @ApiResponse(responseCode = "200", description = "categoria encontrado",
            content = @Content(schema = @Schema(implementation = CategoriaTerapia.class)))
    @ApiResponse(responseCode = "404", description = "categoria no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaTerapia> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo categoria ",
            description = "Registra un categoria con nombre, etc")
    @ApiResponse(responseCode = "201", description = "Categoria creado",
            content = @Content(schema = @Schema(implementation = CategoriaTerapia.class)))
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

    @Operation(summary = "Actualizar un Categoria existente")
    @ApiResponse(responseCode = "200", description = "Categoria actualizado",
            content = @Content(schema = @Schema(implementation = CategoriaTerapia.class)))
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaTerapia> update(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaTerapiaRequest req
    ) {
        CategoriaTerapia updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un categoria por ID ")
    @ApiResponse(responseCode = "204", description = "Categoria eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
