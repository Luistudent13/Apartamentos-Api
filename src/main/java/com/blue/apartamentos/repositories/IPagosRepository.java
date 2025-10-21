package com.blue.apartamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.PagosModel;

@Repository
public interface IPagosRepository extends JpaRepository<PagosModel, Long> {

    // Con PagosModel.reservacion (ManyToOne) y ReservacionModel.id
    List<PagosModel> findByReservacion_Id(Long idReservacion);
}
