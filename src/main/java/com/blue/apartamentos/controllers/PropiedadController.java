package com.blue.apartamentos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.services.PropiedadService;


@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {
    
    @Autowired
    private PropiedadService propiedadService;

    // Aquí irán los métodos para manejar las solicitudes HTTP relacionadas con las propiedades
    // Obtener todas las propiedades, obtener por ID, crear, actualizar, eliminar, etc.
    
    //Recuperar todas la propiedades
    @GetMapping
    public List<PropiedadModel> getAllPropiedades() {
        return propiedadService.getAllPropiedades();
    }

    //Propiedad por ID
    //... Similar a ClienteController
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadModel> getPropiedadesById(@PathVariable Long id) {
        Optional<PropiedadModel> propiedad = propiedadService.getPropiedadById(id);
        return propiedad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nueva propiedad
    @PostMapping
    public PropiedadModel createPropiedad(@RequestBody PropiedadModel propiedad) {
        return propiedadService.savePropiedad(propiedad);
    }

    // Actualizar propiedad existente
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadModel> updatePropiedad(@PathVariable Long id, @RequestBody PropiedadModel propiedadDetails) {
        Optional<PropiedadModel> propiedadOptional = propiedadService.getPropiedadById(id);
        if (propiedadOptional.isPresent()) {
            PropiedadModel propiedadToUpdate = propiedadOptional.get();
            propiedadToUpdate.setDireccion(propiedadDetails.getDireccion());
            propiedadToUpdate.setTipo(propiedadDetails.getTipo());
            propiedadToUpdate.setPrecioNoche(propiedadDetails.getPrecioNoche());
            // Actualizar otros campos según sea necesario

            PropiedadModel updatedPropiedad = propiedadService.savePropiedad(propiedadToUpdate);
            return ResponseEntity.ok(updatedPropiedad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una propiedad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        Optional<PropiedadModel> propiedad = propiedadService.getPropiedadById(id);
        if (propiedad.isPresent()) {
            propiedadService.deletePropiedad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
     // ACTIVIDAD: Crea un metodo para Recuperar propiedades por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PropiedadModel>> getPropiedadesByTipo(@PathVariable PropiedadModel.TipoPropiedad tipo){
        return ResponseEntity.ok(propiedadService.getPropiedadesByTipo(tipo));
    }

    // ACTIVIDAD: Crea un metodo para Recuoperar todas propiedades de un Propietario
    @GetMapping("/propietario/{idPropietario}")
    public ResponseEntity<List<PropiedadModel>> getPropiedadesByPropietario(@PathVariable Long idPropietario){
        return ResponseEntity.ok(propiedadService.getPropiedadesByPropietario(idPropietario));
    }

}