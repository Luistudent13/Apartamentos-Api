package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.UsuarioModel;
import com.blue.apartamentos.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    // Listar todos
    @GetMapping
    public List<UsuarioModel> listar() {
        return servicio.listar();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> obtener(@PathVariable Long id) {
        UsuarioModel u = servicio.buscar(id);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u);
    }

    // Crear
    @PostMapping
    public ResponseEntity<UsuarioModel> crear(@RequestBody UsuarioModel body) {
        UsuarioModel creado = servicio.crear(body);
        return ResponseEntity.ok(creado);
    }

    // Actualizar (PUT) -> actualiza TODOS los campos + setea ultimoLogin = now()
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> actualizar(@PathVariable Long id, @RequestBody UsuarioModel body) {
        UsuarioModel actualizado = servicio.actualizar(id, body);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
