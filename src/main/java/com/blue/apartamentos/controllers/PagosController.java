package com.blue.apartamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.PagosModel;
import com.blue.apartamentos.repositories.IPagosRepository;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {

    @Autowired
    private IPagosRepository repo;

    @PostMapping
    public ResponseEntity<PagosModel> crear(@RequestBody PagosModel p){
        return ResponseEntity.ok(repo.save(p));
    }

    @GetMapping("/reservacion/{idReservacion}")
    public ResponseEntity<List<PagosModel>> porReservacion(@PathVariable Long idReservacion){
        return ResponseEntity.ok(repo.findByReservacion_Id(idReservacion));
    }
}
