package com.blue.apartamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.MensajeModel;

@Repository
public interface IMensajeRepository extends JpaRepository<MensajeModel, Long> {

    List<MensajeModel> findByDestinatarioIdOrderByFechaEnvioDesc(Long idDestinatario);
}
