package com.blue.apartamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.PropiedadModel;

@Repository
public interface IPropiedadRepository extends JpaRepository<PropiedadModel, Long> {
    // Aparte de los metodos heredados de JpaRepository, puedes definir consultas personalizadas aquí

    //Este metodo recupera las propiedades por ciudad y se debe agregar aqui cuando queires aumentar la funcionalidad
    List<PropiedadModel> findByCiudad(String ciudad);

    List<PropiedadModel> findByPais(String pais);
    
}
