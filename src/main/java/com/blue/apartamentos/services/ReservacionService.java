package com.blue.apartamentos.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.models.ReservacionModel.EstadoReservacion;
import com.blue.apartamentos.repositories.IReservacionRepository;

@Service
public class ReservacionService {

    private final IReservacionRepository repo;

    public ReservacionService(IReservacionRepository repo) {
        this.repo = repo;
    }

    // ---------- CRUD sencillo ----------
    public List<ReservacionModel> listar() {
        return repo.findAll();
    }

    public Optional<ReservacionModel> buscar(Long id) {
        return repo.findById(id);
    }

    public ReservacionModel crear(ReservacionModel r) {
        return repo.save(r);
    }

    public ReservacionModel actualizar(Long id, ReservacionModel r) {
        ReservacionModel actual = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe " + id));
        // setea campo por campo (simple)
        actual.setPropiedad(r.getPropiedad());
        actual.setUsuario(r.getUsuario());
        actual.setFechaEntrada(r.getFechaEntrada());
        actual.setFechaSalida(r.getFechaSalida());
        actual.setNumeroHuespedes(r.getNumeroHuespedes());
        actual.setPrecioTotal(r.getPrecioTotal());
        actual.setEstado(r.getEstado());
        actual.setFechaReservacion(r.getFechaReservacion());
        actual.setNotas(r.getNotas());
        actual.setCodigoReserva(r.getCodigoReserva());
        actual.setFechaCheckin(r.getFechaCheckin());
        actual.setFechaCheckout(r.getFechaCheckout());
        return repo.save(actual);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    // ===================== MÉTODOS PARA LAS 17 ACTIVIDADES =====================

// ACT 1: comprobar disponibilidad de un apartamento
public boolean estaDisponible(Long idPropiedad, java.time.LocalDate desde, java.time.LocalDate hasta) {
    var bloqueantes = java.util.List.of(EstadoReservacion.CONFIRMADA, EstadoReservacion.PAGADA);
    boolean traslape = repo.existsByPropiedad_IdPropiedadAndEstadoInAndFechaEntradaLessThanAndFechaSalidaGreaterThan(
        idPropiedad, bloqueantes, hasta, desde
    );
    return !traslape;
}

// ACT 2: reservar un apartamento (simple)
public com.blue.apartamentos.models.ReservacionModel reservar(com.blue.apartamentos.models.ReservacionModel r) {
    if (!estaDisponible(r.getPropiedad().getIdPropiedad(), r.getFechaEntrada(), r.getFechaSalida())) {
        throw new IllegalStateException("La propiedad NO está disponible en ese rango.");
    }
    if (r.getFechaReservacion() == null) {
        r.setFechaReservacion(java.time.LocalDateTime.now());
    }
    if (r.getEstado() == null) {
        r.setEstado(EstadoReservacion.CONFIRMADA);
    }
    return repo.save(r);
}

// ACT 3: cancelar una reservación por id (cambiar estado a CANCELADA)
public com.blue.apartamentos.models.ReservacionModel cancelar(Long id) {
    var r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe reservación " + id));
    r.setEstado(EstadoReservacion.CANCELADA);
    return repo.save(r);
}

// ACT 4: todas por usuario
public List<ReservacionModel> listarPorUsuario(Long idUsuario) {
    return repo.findByUsuario_IdUsuario(idUsuario);  // <-- CAMBIO
}

// ACT 5
public java.util.List<com.blue.apartamentos.models.ReservacionModel> listarPorPropiedad(Long idPropiedad) {
    return repo.findByPropiedad_IdPropiedad(idPropiedad);
}

// ACT 6 (alias)
public java.util.List<com.blue.apartamentos.models.ReservacionModel> listarTodas() {
    return repo.findAll();
}

// ACT 7 (actualizar simple)
public com.blue.apartamentos.models.ReservacionModel actualizarSimple(Long id, com.blue.apartamentos.models.ReservacionModel r) {
    var actual = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe reservación " + id));
    actual.setPropiedad(r.getPropiedad());
    actual.setUsuario(r.getUsuario());
    actual.setFechaEntrada(r.getFechaEntrada());
    actual.setFechaSalida(r.getFechaSalida());
    actual.setNumeroHuespedes(r.getNumeroHuespedes());
    actual.setPrecioTotal(r.getPrecioTotal());
    actual.setEstado(r.getEstado());
    actual.setFechaReservacion(r.getFechaReservacion());
    actual.setNotas(r.getNotas());
    actual.setCodigoReserva(r.getCodigoReserva());
    actual.setFechaCheckin(r.getFechaCheckin());
    actual.setFechaCheckout(r.getFechaCheckout());
    return repo.save(actual);
}

// ACT 8
public java.util.Optional<com.blue.apartamentos.models.ReservacionModel> obtenerPorId(Long id) {
    return repo.findById(id);
}

// ACT 9
public java.util.List<com.blue.apartamentos.models.ReservacionModel> listarPorFecha(java.time.LocalDate desde, java.time.LocalDate hasta) {
    return repo.findByFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(hasta, desde);
}

// ACT 10
public java.util.List<com.blue.apartamentos.models.ReservacionModel> listarPorEstado(EstadoReservacion estado) {
    return repo.findByEstado(estado);
}

// ACT 11: usuario y estado
public List<ReservacionModel> listarPorUsuarioYEstado(Long idUsuario, EstadoReservacion estado) {
    return repo.findByUsuario_IdUsuarioAndEstado(idUsuario, estado);  // <-- CAMBIO
}

// ACT 12
public java.util.List<com.blue.apartamentos.models.ReservacionModel> listarPorPropiedadYEstado(Long idPropiedad, EstadoReservacion estado) {
    return repo.findByPropiedad_IdPropiedadAndEstado(idPropiedad, estado);
}

// ACT 13
public List<ReservacionModel> listarPorUsuarioYFecha(Long idUsuario, LocalDate desde, LocalDate hasta) {
    return repo.findByUsuario_IdUsuarioAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(idUsuario, hasta, desde); // <-- CAMBIO
}

// ACT 14
public java.util.List<com.blue.apartamentos.models.ReservacionModel> listarPorPropiedadYFecha(
        Long idPropiedad, java.time.LocalDate desde, java.time.LocalDate hasta) {
    return repo.findByPropiedad_IdPropiedadAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(idPropiedad, hasta, desde);
}

// ACT 15
public int cancelarTodasPorUsuario(Long idUsuario) {
    var lista = repo.findByUsuario_IdUsuario(idUsuario);  // <-- CAMBIO
    lista.forEach(r -> r.setEstado(EstadoReservacion.CANCELADA));
    repo.saveAll(lista);
    return lista.size();
}

// ACT 16
public int cancelarTodasPorPropiedad(Long idPropiedad) {
    var lista = repo.findByPropiedad_IdPropiedad(idPropiedad);
    lista.forEach(r -> r.setEstado(EstadoReservacion.CANCELADA));
    repo.saveAll(lista);
    return lista.size();
}

// ACT 17
public int cancelarTodasPorFecha(java.time.LocalDate desde, java.time.LocalDate hasta) {
    var lista = repo.findByFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(hasta, desde);
    lista.forEach(r -> r.setEstado(EstadoReservacion.CANCELADA));
    repo.saveAll(lista);
    return lista.size();
}
}
