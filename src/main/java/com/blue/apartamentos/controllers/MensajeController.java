package com.blue.apartamentos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.MensajeModel;
import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.repositories.IClienteRepository;
import com.blue.apartamentos.repositories.IMensajeRepository;
import com.blue.apartamentos.repositories.IReservacionRepository;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired private IMensajeRepository repo;
    @Autowired private IClienteRepository clienteRepo;
    @Autowired private IReservacionRepository reservRepo;

    @PostMapping
    public ResponseEntity<MensajeModel> enviar(@RequestBody MensajeModel m){
        ClienteModel r = clienteRepo.findById(m.getRemitente().getId()).orElse(null);
        ClienteModel d = clienteRepo.findById(m.getDestinatario().getId()).orElse(null);
        if(r == null || d == null) return ResponseEntity.badRequest().build();
        m.setRemitente(r);
        m.setDestinatario(d);

        if (m.getReservacion() != null && m.getReservacion().getId() != null) {
            ReservacionModel res = reservRepo.findById(m.getReservacion().getId()).orElse(null);
            m.setReservacion(res);
        }
        return ResponseEntity.ok(repo.save(m));
    }

    @GetMapping("/inbox/{idUsuario}")
    public ResponseEntity<List<MensajeModel>> inbox(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(repo.findByDestinatarioIdOrderByFechaEnvioDesc(idUsuario));
    }
}
