package com.blue.apartamentos.controllers;

import com.blue.apartamentos.services.DisponibilidadService;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disponibilidad")
public class DisponibilidadController {
  private final DisponibilidadService servicio;
  public DisponibilidadController(DisponibilidadService s){ this.servicio = s; }

  // GET /disponibilidad/comprobar?idPropiedad=1&desde=2025-11-10&hasta=2025-11-15
  @GetMapping("/comprobar")
  public boolean comprobar(@RequestParam Long idPropiedad,
                           @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate desde,
                           @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate hasta){
    return servicio.estaDisponible(idPropiedad, desde, hasta);
  }
}
