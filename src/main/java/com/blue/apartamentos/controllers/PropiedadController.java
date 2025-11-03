package com.blue.apartamentos.controllers;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.services.PropiedadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {

    private final PropiedadService servicio;

    public PropiedadController(PropiedadService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<PropiedadModel> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropiedadModel> obtener(@PathVariable Long id) {
        PropiedadModel p = servicio.buscar(id);
        if (p == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    // Crear: rehidrata propietario
    @PostMapping
    public ResponseEntity<PropiedadModel> crear(@RequestBody PropiedadModel body) {
        return ResponseEntity.ok(servicio.crear(body));
    }

    // Actualizar: copia TODOS los campos y setea fechaActualizacion = now
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadModel> actualizar(@PathVariable Long id, @RequestBody PropiedadModel body) {
        return ResponseEntity.ok(servicio.actualizar(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
