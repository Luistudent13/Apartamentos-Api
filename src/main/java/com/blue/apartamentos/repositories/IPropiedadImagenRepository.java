package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.PropiedadImagenModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPropiedadImagenRepository extends JpaRepository<PropiedadImagenModel, Long> {

    List<PropiedadImagenModel> findByPropiedad_IdPropiedadOrderByOrdenAsc(Long idPropiedad);
}
