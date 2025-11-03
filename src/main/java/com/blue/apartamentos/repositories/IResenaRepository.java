// ResenaRepository.java
package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.ResenaModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IResenaRepository extends JpaRepository<ResenaModel, Long> {

    List<ResenaModel> findByReservacion_IdReservacion(Long id);
}
