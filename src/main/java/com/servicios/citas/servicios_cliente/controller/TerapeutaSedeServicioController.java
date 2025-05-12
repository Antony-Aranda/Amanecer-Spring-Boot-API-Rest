package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.entity.Terapeuta;
import com.servicios.citas.servicios_cliente.model.entity.TerapeutaSedeServicio;
import com.servicios.citas.servicios_cliente.model.entity.Usuario;
import com.servicios.citas.servicios_cliente.service.TerapeutaSedeServicioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Tag(name = "TerapeutaSedeServicios", description = "Gestión de asignaciones")  // Agrupa y describe el controlador :contentReference[oaicite:2]{index=2}
@RestController
@RequestMapping("/api/sedes-servicios/{ssId}/terapeutas")
public class TerapeutaSedeServicioController {
    private final TerapeutaSedeServicioServicio service;

    public TerapeutaSedeServicioController(TerapeutaSedeServicioServicio service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los Terapeutas de una sede y encargado a 1 servicio",
            description = "Devuelve la lista completa de terapeutas registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TerapeutaSedeServicio.class)))

    @GetMapping
    public List<Terapeuta> list(@PathVariable Long ssId) {
        return service.listByAsignacion(ssId);
    }


    @Operation(summary = "Agrega un terapeuta a una sede y encargadi de 1 servicio",
            description = "Registra un terapeuta con id_terapeuta")
    @ApiResponse(responseCode = "201", description = "Terapeuta asignado",
            content = @Content(schema = @Schema(implementation = TerapeutaSedeServicio.class)))
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

    @Operation(summary = "Eliminar un terapeuta por ID de una sede y servicio")
    @ApiResponse(responseCode = "204", description = "Terapeuta eliminado", content = @Content)
    @DeleteMapping("/{terapeutaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long ssId,
                       @PathVariable Long terapeutaId) {
        service.remove(ssId, terapeutaId);
    }
}
