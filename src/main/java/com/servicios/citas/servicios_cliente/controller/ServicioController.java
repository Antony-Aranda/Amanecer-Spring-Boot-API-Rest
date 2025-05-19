package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.ClienteRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.Cliente;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.ClienteService;
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

@Tag(name = "Servicio", description = "Gestión de servicios")
@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class ServicioController {
    private final ServicioServicio service;

    public ServicioController(ServicioServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los Servicios",
            description = "Devuelve la lista completa de servicios registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Servicio.class)))
    @GetMapping
    public ResponseEntity<List<Servicio>> all() {
        return ResponseEntity.ok(service.listAll());
    }


    @Operation(summary = "Obtener un servicio por ID",
            description = "Busca un servicio existente usando su identificador")
    @ApiResponse(responseCode = "200", description = "Servicio encontrado",
            content = @Content(schema = @Schema(implementation = Servicio.class)))
    @ApiResponse(responseCode = "404", description = "Servicio no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Crear un nuevo SERVICIO",
            description = "Registra un SERVICIO con nombre,descripcion, precion ,....")
    @ApiResponse(responseCode = "201", description = "SERVICIO creado",
            content = @Content(schema = @Schema(implementation = Servicio.class)))
    @PostMapping
    public ResponseEntity<Servicio> create(@RequestBody @Valid ServicioRequest req) {
        Servicio saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }


    @Operation(summary = "Actualizar un Servicio existente")
    @ApiResponse(responseCode = "200", description = "Servicio actualizado",
            content = @Content(schema = @Schema(implementation = Servicio.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> update(
            @PathVariable Long id,
            @RequestBody @Valid ServicioRequest req
    ) {
        Servicio updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un Servicio por ID")
    @ApiResponse(responseCode = "204", description = "Servicio eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
