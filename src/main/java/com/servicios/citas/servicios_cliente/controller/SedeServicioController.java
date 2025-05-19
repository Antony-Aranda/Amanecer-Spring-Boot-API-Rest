package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.SedeServicioRequest;
import com.servicios.citas.servicios_cliente.model.dto.ServicioRequest;
import com.servicios.citas.servicios_cliente.model.entity.SedeServicio;
import com.servicios.citas.servicios_cliente.model.entity.Servicio;
import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.SedeServicioServicio;
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

@Tag(name = "SedeServicios", description = "Gestión de sedeServicios")
@RestController
@RequestMapping("/api/sedes-servicios")
@CrossOrigin(origins = "*")
public class SedeServicioController {
    private final SedeServicioServicio service;

    public SedeServicioController(SedeServicioServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los sedes y servicios",
            description = "Devuelve la lista completa de sedes y servicios registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SedeServicio.class)))

    @GetMapping
    public ResponseEntity<List<SedeServicio>> all() {
        return ResponseEntity.ok(service.listAll());
    }


    @Operation(summary = "Obtener un sede-servicio por ID",
            description = "Busca un sede-servicio existente usando su identificador")
    @ApiResponse(responseCode = "200", description = "sede-servicio encontrado",
            content = @Content(schema = @Schema(implementation = SedeServicio.class)))
    @ApiResponse(responseCode = "404", description = "sede-servicio no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<SedeServicio> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo sede-servicio",
            description = "Registra un sede-servicio con id_sede y id_servicio")
    @ApiResponse(responseCode = "201", description = "sede-servicio creado",
            content = @Content(schema = @Schema(implementation = SedeServicio.class)))
    @PostMapping
    public ResponseEntity<SedeServicio> create(@RequestBody @Valid SedeServicioRequest req) {
        SedeServicio saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<SedeServicio> update(
            @PathVariable Long id,
            @RequestBody @Valid SedeServicioRequest req
    ) {
        SedeServicio updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }*/
   @Operation(summary = "Eliminar un Servicio-sede por ID")
   @ApiResponse(responseCode = "204", description = "Servicio-sede eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
