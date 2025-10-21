package com.blue.apartamentos.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.ReservacionModel;

@Repository
public interface IReservacionRepository extends JpaRepository<ReservacionModel, Long> {

    List<ReservacionModel> findByCliente_Id(Long idCliente);

    List<ReservacionModel> findByPropiedad_Id(Long idPropiedad);

    // Traslape: (entradaA <= salidaB) && (salidaA >= entradaB)
    boolean existsByPropiedad_IdAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
        Long idPropiedad, LocalDate fin, LocalDate ini
    );
}
