package com.blue.apartamentos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> getAll() {
        return ResponseEntity.ok(service.getAllClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getById(@PathVariable Long id) {
        Optional<ClienteModel> c = service.getClienteById(id);
        return c.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

@PostMapping
public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel cliente) {
    ClienteModel saved = service.saveCliente(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
}

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> update(@PathVariable Long id, @RequestBody ClienteModel changes) {
        return service.updateCliente(id, changes)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<ClienteModel> c = service.getClienteById(id);
        if (c.isPresent()) {
            service.deleteCliente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
