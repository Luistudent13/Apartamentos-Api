package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.ResenaModel;
import com.blue.apartamentos.services.ResenaService; // OJO: corrige el package si es "apartamentos"

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    private final ResenaService service;
    public ResenaController(ResenaService service){ this.service = service; }

    @PostMapping public ResenaModel crear(@RequestBody ResenaModel r){ return service.guardar(r); }
    @GetMapping("/reservacion/{id}") public List<ResenaModel> porReservacion(@PathVariable Long id){
        return service.porReservacion(id);
    }
}
