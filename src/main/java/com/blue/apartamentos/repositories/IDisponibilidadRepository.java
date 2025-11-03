// DisponibilidadRepository.java
package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.DisponibilidadModel;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDisponibilidadRepository extends JpaRepository<DisponibilidadModel, Long> {
    long countByPropiedad_IdPropiedadAndFechaBetweenAndDisponibleTrue(Long idPropiedad, LocalDate desde,
            LocalDate hasta);
}
