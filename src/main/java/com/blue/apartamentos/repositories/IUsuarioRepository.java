package com.blue.apartamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.UsuarioModel;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByDocumentoIdentidad(String documentoIdentidad);

    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}
