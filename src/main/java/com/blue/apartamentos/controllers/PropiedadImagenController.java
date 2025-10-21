package com.blue.apartamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.PropiedadImagenModel;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.repositories.IPropiedadRepository;
import com.blue.apartamentos.services.PropiedadImagenService;

@RestController
@RequestMapping("/api/propiedades/{idPropiedad}/imagenes")
public class PropiedadImagenController {

    @Autowired
    private PropiedadImagenService imagenService;

    @Autowired
    private IPropiedadRepository propiedadRepo;

    @PostMapping
    public ResponseEntity<PropiedadImagenModel> addImagen(
            @PathVariable Long idPropiedad,
            @RequestBody PropiedadImagenModel body) {
        PropiedadModel prop = propiedadRepo.findById(idPropiedad).orElse(null);
        if (prop == null)
            return ResponseEntity.notFound().build();
        body.setPropiedad(prop);
        return ResponseEntity.ok(imagenService.save(body));
    }

    @GetMapping
    public ResponseEntity<List<PropiedadImagenModel>> list(@PathVariable Long idPropiedad) {
        return ResponseEntity.ok(imagenService.listByPropiedad(idPropiedad));
    }
}
