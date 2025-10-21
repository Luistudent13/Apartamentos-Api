package com.blue.apartamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.ResenaModel;

@Repository
public interface IResenaRepository extends JpaRepository<ResenaModel, Long> {
    // reservacion.propiedad.id
    List<ResenaModel> findByReservacionPropiedadId(Long idPropiedad);
}
