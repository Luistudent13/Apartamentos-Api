package com.blue.apartamentos.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.MensajeModel;
import com.blue.apartamentos.repositories.IMensajeRepository;

@Service
public class MensajeService {

    private final IMensajeRepository mensajeRepository;

    public MensajeService(IMensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    public List<MensajeModel> mensajesPorReservacion(Long idReservacion) {
        return mensajeRepository.findByRemitente_IdUsuarioOrDestinatario_IdUsuario(idReservacion, idReservacion);
    }

    public List<MensajeModel> mensajesDelUsuario(Long idUsuario) {
        return mensajeRepository.findByRemitente_IdUsuarioOrDestinatario_IdUsuario(idUsuario, idUsuario);
    }

    public MensajeModel guardar(MensajeModel m) {
        return mensajeRepository.save(m);
    }
}