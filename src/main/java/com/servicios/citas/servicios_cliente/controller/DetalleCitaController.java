package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.DetalleCitaRequest;
import com.servicios.citas.servicios_cliente.model.entity.DetalleCita;
import com.servicios.citas.servicios_cliente.model.entity.RangoDuracion;
import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.service.DetalleCitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Detalles", description = "Gestión de detalles")
@RestController
@RequestMapping("/api/citas/{citaId}/detalles")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class DetalleCitaController {
    private final DetalleCitaService service;

    public DetalleCitaController(DetalleCitaService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los detalles de una cita ",
            description = "Devuelve la lista completa de detalles x cita  registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DetalleCita.class)))
    @GetMapping
    public List<DetalleCita> list(@PathVariable Long citaId) {
        return service.list(citaId);
    }



    @Operation(summary = "Crear un nuevo detalle para una cita",
            description = "Registra un detalle con sub_total, id cita , id bloque , etc")
    @ApiResponse(responseCode = "201", description = "Detalle creado",
            content = @Content(schema = @Schema(implementation = DetalleCita.class)))
    @PostMapping
    public ResponseEntity<DetalleCita> add(
            @PathVariable Long citaId,
            @RequestBody @Valid DetalleCitaRequest req) {

        DetalleCita created = service.add(citaId, req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @Operation(summary = "Eliminar un detalle por ID de una cita")
    @ApiResponse(responseCode = "204", description = "Detalle eliminado", content = @Content)
    @DeleteMapping("/{detId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(
            @PathVariable Long citaId,
            @PathVariable Long detId) {
        service.remove(citaId, detId);
    }
}
