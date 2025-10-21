package com.blue.apartamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.ResenaModel;
import com.blue.apartamentos.repositories.IResenaRepository;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private IResenaRepository repo;

    @PostMapping
    public ResponseEntity<ResenaModel> create(@RequestBody ResenaModel r) {
        return ResponseEntity.ok(repo.save(r));
    }

    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<ResenaModel>> porPropiedad(@PathVariable Long idPropiedad) {
        return ResponseEntity.ok(repo.findByReservacionPropiedadId(idPropiedad));
    }
}
