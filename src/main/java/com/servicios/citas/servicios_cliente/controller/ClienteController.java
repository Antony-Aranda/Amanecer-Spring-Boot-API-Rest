package com.servicios.citas.servicios_cliente.controller;

import com.servicios.citas.servicios_cliente.model.dto.ClienteRequest;
import com.servicios.citas.servicios_cliente.model.entity.Cliente;
import com.servicios.citas.servicios_cliente.model.entity.DetalleCita;
import com.servicios.citas.servicios_cliente.model.entity.Sede;
import com.servicios.citas.servicios_cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.net.URI;
import java.util.List;

@Tag(name = "Clientes", description = "Gestión de clientes")
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar todos los clientes ",
            description = "Devuelve la lista completa de clientes  registrados"
    )
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)))
    @GetMapping
    public ResponseEntity<List<Cliente>> all() {
        return ResponseEntity.ok(service.listAll());
    }



    @Operation(summary = "Obtener un cliente por ID",
            description = "Busca un cliente existente usando su identificador")
    @ApiResponse(responseCode = "200", description = "cliente encontrado",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "404", description = "cliente no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getOne(@PathVariable Long id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Crear un nuevo cliente ",
            description = "Registra un cliente con nombre, apellido, dni, etc")
    @ApiResponse(responseCode = "201", description = "Cliente creado",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody @Valid ClienteRequest req) {
        Cliente saved = service.create(req);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }


    @Operation(summary = "Actualizar un Cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(
            @PathVariable Long id,
            @RequestBody @Valid ClienteRequest req
    ) {
        Cliente updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar un Cliente por ID ")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
