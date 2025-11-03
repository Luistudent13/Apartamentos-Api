package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.PagosModel;
import com.blue.apartamentos.services.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService service;
    public PagoController(PagoService service){ this.service = service; }

    @PostMapping public PagosModel crear(@RequestBody PagosModel p){ return service.guardar(p); }
    @GetMapping("/reservacion/{id}") public List<PagosModel> porReservacion(@PathVariable Long id){
        return service.porReservacion(id);
    }
}
