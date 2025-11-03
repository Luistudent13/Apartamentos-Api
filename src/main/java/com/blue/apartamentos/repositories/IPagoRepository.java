// PagoRepository.java
package com.blue.apartamentos.repositories;

import com.blue.apartamentos.models.PagosModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepository extends JpaRepository<PagosModel, Long> {

    List<PagosModel> findByReservacion_IdReservacion(Long id);
}
