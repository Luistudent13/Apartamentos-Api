package com.blue.apartamentos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.MensajeModel;

public interface IMensajeRepository extends JpaRepository<MensajeModel, Long> {

    // ✅ USAR este (coincide con la PK real de UsuarioModel: idUsuario)
    List<MensajeModel> findByRemitente_IdUsuarioOrDestinatario_IdUsuario(Long remitenteId, Long destinatarioId);

    // (opcional, ordenado por fecha)
    List<MensajeModel> findByRemitente_IdUsuarioOrDestinatario_IdUsuarioOrderByFechaEnvioDesc(Long u1, Long u2);
}
