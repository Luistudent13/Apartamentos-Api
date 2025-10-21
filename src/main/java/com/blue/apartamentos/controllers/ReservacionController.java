package com.blue.apartamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.repositories.IClienteRepository;
import com.blue.apartamentos.repositories.IPropiedadRepository;
import com.blue.apartamentos.repositories.IReservacionRepository;
import com.blue.apartamentos.services.ReservacionService;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    @Autowired private IReservacionRepository repo;
    @Autowired private IPropiedadRepository propRepo;
    @Autowired private IClienteRepository cliRepo;
    @Autowired private ReservacionService service;

    @PostMapping
    public ResponseEntity<ReservacionModel> crear(@RequestBody ReservacionModel r){
        // Resolver referencias por id (si vienen sólo con id)
        if (r.getPropiedad() != null && r.getPropiedad().getId() != null) {
            PropiedadModel p = propRepo.findById(r.getPropiedad().getId()).orElse(null);
            if (p == null) return ResponseEntity.badRequest().build();
            r.setPropiedad(p);
        }
        if (r.getCliente() != null && r.getCliente().getId() != null) {
            ClienteModel c = cliRepo.findById(r.getCliente().getId()).orElse(null);
            if (c == null) return ResponseEntity.badRequest().build();
            r.setCliente(c);
        }
        return ResponseEntity.ok(service.crear(r));
    }

    @GetMapping
    public ResponseEntity<List<ReservacionModel>> all(){ return ResponseEntity.ok(repo.findAll()); }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReservacionModel>> porCliente(@PathVariable Long idCliente){
        return ResponseEntity.ok(service.deCliente(idCliente));
    }

    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<ReservacionModel>> porPropiedad(@PathVariable Long idPropiedad){
        return ResponseEntity.ok(service.dePropiedad(idPropiedad));
    }
}
