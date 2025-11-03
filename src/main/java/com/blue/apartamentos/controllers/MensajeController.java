package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.MensajeModel;
import com.blue.apartamentos.services.MensajeService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("/reservacion/{id}")
    public List<MensajeModel> porReservacion(@PathVariable Long id) {
        return mensajeService.mensajesPorReservacion(id);
    }

    @GetMapping("/usuario/{id}")
    public List<MensajeModel> delUsuario(@PathVariable Long id) {
        return mensajeService.mensajesDelUsuario(id);
    }

    @PostMapping
    public MensajeModel crear(@RequestBody MensajeModel m) {
        return mensajeService.guardar(m);
    }
}
