package com.blue.apartamentos.services;

import com.blue.apartamentos.repositories.IDisponibilidadRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class DisponibilidadService {
    private final IDisponibilidadRepository disponibilidadRepository;

    public DisponibilidadService(IDisponibilidadRepository repo) {
        this.disponibilidadRepository = repo;
    }

    // Comprueba que TODOS los días [desde, hasta) estén disponibles
    public boolean estaDisponible(Long idPropiedad, LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null || !hasta.isAfter(desde))
            return false;
        long dias = java.time.temporal.ChronoUnit.DAYS.between(desde, hasta);
        long diasDisponibles = disponibilidadRepository
                .countByPropiedad_IdPropiedadAndFechaBetweenAndDisponibleTrue(idPropiedad, desde, hasta.minusDays(1));
        return diasDisponibles >= dias;
    }
}
