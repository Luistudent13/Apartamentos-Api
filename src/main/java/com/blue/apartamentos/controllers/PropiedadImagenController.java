package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.PropiedadImagenModel;
import com.blue.apartamentos.services.PropiedadImagenService;

@RestController
@RequestMapping("/propiedad-imagenes")
public class PropiedadImagenController {

    private final PropiedadImagenService service;

    public PropiedadImagenController(PropiedadImagenService service) {
        this.service = service;
    }

    @GetMapping("/propiedad-imagenes/{idPropiedad}")
    public List<PropiedadImagenModel> listar(@PathVariable Long idPropiedad) {
        return service.porPropiedad(idPropiedad);
    }

    @PostMapping
    public PropiedadImagenModel crear(@RequestBody PropiedadImagenModel m) {
        return service.guardar(m);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
