package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.SedeRequest;
import com.servicios.citas.servicios_cliente.model.dto.UsuarioRequest;

import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.SedeServicio;
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

@Tag(name = "Sedes", description = "Gestión de sedes")
@RestController
@RequestMapping("/api/sedes")
@CrossOrigin(origins = "*")
public class SedeController {

    private final SedeServicio service;

    public SedeController(SedeServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los sedes ",
            description = "Devuelve la lista completa de sedes  registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Sede.class)))
    @GetMapping
    public ResponseEntity<List<Sede>> all() {
        return ResponseEntity.ok(service.listAll());
    }

    @Operation(summary = "Obtener un sede por ID",
            description = "Busca un sede existente usando su identificador")
    @ApiResponse(responseCode = "200", description = "sede encontrado",
            content = @Content(schema = @Schema(implementation = Sede.class)))
    @ApiResponse(responseCode = "404", description = "sede no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Sede> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @Operation(summary = "Crear un nuevo sede",
            description = "Registra un sede con departamneto , dirrecion , etc")
    @ApiResponse(responseCode = "201", description = "sede creado",
            content = @Content(schema = @Schema(implementation = Sede.class)))
    @PostMapping
    public ResponseEntity<Sede> create(@RequestBody @Valid SedeRequest req) {
        Sede saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @Operation(summary = "Actualizar un Sede existente")
    @ApiResponse(responseCode = "200", description = "Sede actualizado",
            content = @Content(schema = @Schema(implementation = Sede.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Sede> update(
            @PathVariable Long id,
            @RequestBody @Valid SedeRequest req
    ) {
        Sede updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un Sede por ID")
    @ApiResponse(responseCode = "204", description = "Sede eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
