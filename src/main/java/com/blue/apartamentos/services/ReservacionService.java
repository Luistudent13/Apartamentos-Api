package com.blue.apartamentos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.models.ReservacionModel.EstadoReservacion;
import com.blue.apartamentos.repositories.IReservacionRepository;

@Service
public class ReservacionService {

    @Autowired
    private IReservacionRepository repo;

    public ReservacionModel crear(ReservacionModel r) {
        // validación simple de traslapes
        boolean ocupado = repo.existsByPropiedad_IdAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                r.getPropiedad().getId(), r.getFechaSalida(), r.getFechaEntrada());
        if (ocupado) {
            throw new IllegalStateException("Fechas no disponibles");
        }
        r.setEstado(EstadoReservacion.CONFIRMADA);
        return repo.save(r);
    }

    public List<ReservacionModel> deCliente(Long idCliente) {
        return repo.findByCliente_Id(idCliente);
    }

    public List<ReservacionModel> dePropiedad(Long idPropiedad) {
        return repo.findByPropiedad_Id(idPropiedad);
    }
}
