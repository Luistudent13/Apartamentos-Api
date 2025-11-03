package com.blue.apartamentos.repositories;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blue.apartamentos.models.ReservacionModel;
// OJO: tu enum está anidado en ReservacionModel:
import com.blue.apartamentos.models.ReservacionModel.EstadoReservacion;

public interface IReservacionRepository extends JpaRepository<ReservacionModel, Long> {

        // ACT 1: traslape para disponibilidad (start < hasta && end > desde)
        boolean existsByPropiedad_IdPropiedadAndEstadoInAndFechaEntradaLessThanAndFechaSalidaGreaterThan(
                        Long idPropiedad,
                        Collection<EstadoReservacion> estados,
                        LocalDate hasta,
                        LocalDate desde);

        // ACT 4: por usuario (cliente)
        List<ReservacionModel> findByUsuario_IdUsuario(Long idUsuario);

        // ACT 5: por propiedad (apartamento)
        List<ReservacionModel> findByPropiedad_IdPropiedad(Long idPropiedad);

        // ACT 9: por rango de fechas (cualquier traslape con [desde,hasta])
        List<ReservacionModel> findByFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                        LocalDate hasta,
                        LocalDate desde);

        // ACT 10: por estado
        List<ReservacionModel> findByEstado(EstadoReservacion estado);

        // ACT 11: por usuario y estado
        List<ReservacionModel> findByUsuario_IdUsuarioAndEstado(
                        Long idUsuario,
                        EstadoReservacion estado);

        // ACT 12: por propiedad y estado
        List<ReservacionModel> findByPropiedad_IdPropiedadAndEstado(Long idPropiedad, EstadoReservacion estado);

        // ACT 13: por usuario y fecha (traslape)
        List<ReservacionModel> findByUsuario_IdUsuarioAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                        Long idUsuario,
                        LocalDate hasta,
                        LocalDate desde);

        // ACT 14: por propiedad y fecha (traslape)
        List<ReservacionModel> findByPropiedad_IdPropiedadAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                        Long idPropiedad,
                        LocalDate hasta,
                        LocalDate desde);

        // extra útil:
        Optional<ReservacionModel> findByCodigoReserva(String codigoReserva);
}
