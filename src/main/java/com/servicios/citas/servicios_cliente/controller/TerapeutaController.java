package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.TerapeutaRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;
import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.TerapeutaServicio;
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

@Tag(name = "Terapeutas", description = "Gestión de terapeutas")
@RestController
@RequestMapping("/api/terapeutas")
public class TerapeutaController {

    private final TerapeutaServicio service;

    public TerapeutaController(TerapeutaServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los Terapeutas",
            description = "Devuelve la lista completa de Terapeutas registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Terapeuta.class)))

    @GetMapping
    public ResponseEntity<List<Terapeuta>> all() {
        return ResponseEntity.ok(service.listAll());
    }


    @Operation(summary = "Obtener un terapeuta por ID",
            description = "Busca un terapeuta existente usando su identificador")
    @ApiResponse(responseCode = "200", description = "Terapeuta encontrado",
            content = @Content(schema = @Schema(implementation = Terapeuta.class)))
    @ApiResponse(responseCode = "404", description = "Terapeta no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Terapeuta> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo Terapeuta",
            description = "Registra un Terapeuta con nombre,apellido y dni")
    @ApiResponse(responseCode = "201", description = "Terapeuta creado",
            content = @Content(schema = @Schema(implementation = Terapeuta.class)))
    @PostMapping
    public ResponseEntity<Terapeuta> create(@RequestBody @Valid TerapeutaRequest req) {
        Terapeuta saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @Operation(summary = "Actualizar un Terapeuta existente")
    @ApiResponse(responseCode = "200", description = "Terapeuta actualizado",
            content = @Content(schema = @Schema(implementation = Terapeuta.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Terapeuta> update(
            @PathVariable Long id,
            @RequestBody @Valid TerapeutaRequest req
    ) {
        Terapeuta updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un Terapeuta por ID")
    @ApiResponse(responseCode = "204", description = "Terapeuta eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
