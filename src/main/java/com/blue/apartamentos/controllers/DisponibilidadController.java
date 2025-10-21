package com.blue.apartamentos.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.DisponibilidadModel;
import com.blue.apartamentos.repositories.IDisponibilidadRepository;

@RestController
@RequestMapping("/api/disponibilidades")
@CrossOrigin(origins = "*")
public class DisponibilidadController {

    @Autowired
    private IDisponibilidadRepository disponibilidadRepository;

    @GetMapping("/propiedad/{idPropiedad}")
    public ResponseEntity<List<DisponibilidadModel>> porRango(
            @PathVariable Long idPropiedad,
            @RequestParam String desde,
            @RequestParam String hasta) {

        LocalDate iniDate = LocalDate.parse(desde);
        LocalDate finDate = LocalDate.parse(hasta);

        LocalDateTime ini = iniDate.atStartOfDay();
        LocalDateTime fi = finDate.atTime(23, 59, 59);

        List<DisponibilidadModel> lista = disponibilidadRepository
            .findByPropiedad_IdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(idPropiedad, fi, ini);

        return ResponseEntity.ok(lista);
    }
}
