package com.blue.apartamentos.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.DisponibilidadModel;

@Repository
public interface IDisponibilidadRepository extends JpaRepository<DisponibilidadModel, Long> {

    List<DisponibilidadModel> findByPropiedad_IdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
            Long propiedadId,
            LocalDateTime fin, // <=
            LocalDateTime ini // >=
    );
}
