package com.blue.apartamentos.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.ReservacionModel.EstadoReservacion;
import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.services.ReservacionService;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

  private final ReservacionService service;

  public ReservacionController(ReservacionService service) {
    this.service = service;
  }

  // ------ CRUD básico ------
  @GetMapping
  public ResponseEntity<List<ReservacionModel>> listar() {
    return ResponseEntity.ok(service.listar());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReservacionModel> buscar(@PathVariable Long id) {
    return service.buscar(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ReservacionModel> crear(@RequestBody ReservacionModel body) {
    return ResponseEntity.ok(service.crear(body));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReservacionModel> actualizar(@PathVariable Long id, @RequestBody ReservacionModel body) {
    return ResponseEntity.ok(service.actualizar(id, body));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    service.eliminar(id);
    return ResponseEntity.noContent().build();
  }

  // ------ Métodos de práctica del enunciado ------
  // 1) Disponibilidad
  @GetMapping("/disponible")
  public Map<String, Object> disponible(
      @RequestParam Long idPropiedad,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

    boolean disponible = service.estaDisponible(idPropiedad, desde, hasta);
    return Map.of("ok", true, "idPropiedad", idPropiedad, "desde", desde, "hasta", hasta, "disponible", disponible);
  }

  // 2) Reservar
  @PostMapping("/reservar")
  public ResponseEntity<ReservacionModel> reservar(@RequestBody ReservacionModel body) {
    return ResponseEntity.ok(service.reservar(body));
  }

  // ===================== ENDPOINTS PARA LAS 17 ACTIVIDADES =====================

  // ACT 1: comprobar disponibilidad
  @GetMapping("/actividades/disponible")
  public java.util.Map<String, Object> act1_disponible(
      @RequestParam Long idPropiedad,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate desde,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate hasta) {
    boolean ok = service.estaDisponible(idPropiedad, desde, hasta);
    return java.util.Map.of("ok", true, "idPropiedad", idPropiedad, "desde", desde, "hasta", hasta, "disponible", ok);
  }

  // ACT 2: reservar
  @PostMapping("/actividades/reservar")
  public org.springframework.http.ResponseEntity<com.blue.apartamentos.models.ReservacionModel> act2_reservar(
      @RequestBody com.blue.apartamentos.models.ReservacionModel body) {
    return org.springframework.http.ResponseEntity.ok(service.reservar(body));
  }

  // ACT 3: cancelar una reservación por id
  @PostMapping("/actividades/{id}/cancelar")
  public org.springframework.http.ResponseEntity<com.blue.apartamentos.models.ReservacionModel> act3_cancelar(
      @PathVariable Long id) {
    return org.springframework.http.ResponseEntity.ok(service.cancelar(id));
  }

  // ACT 4: todas por usuario
  @GetMapping("/actividades/usuario/{idUsuario}")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act4_porUsuario(@PathVariable Long idUsuario) {
    return service.listarPorUsuario(idUsuario);
  }

  // ACT 5: todas por apartamento
  @GetMapping("/actividades/propiedad/{idPropiedad}")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act5_porPropiedad(
      @PathVariable Long idPropiedad) {
    return service.listarPorPropiedad(idPropiedad);
  }

  // ACT 6: todas (alias)
  @GetMapping("/actividades/todas")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act6_todas() {
    return service.listarTodas();
  }

  // ACT 7: actualizar una reservación
  @PutMapping("/actividades/{id}")
  public org.springframework.http.ResponseEntity<com.blue.apartamentos.models.ReservacionModel> act7_actualizar(
      @PathVariable Long id, @RequestBody com.blue.apartamentos.models.ReservacionModel body) {
    return org.springframework.http.ResponseEntity.ok(service.actualizarSimple(id, body));
  }

 //ACT 8: obtener por id, me fallaba, ya existe
  @GetMapping("actividades//{id}")
  public org.springframework.http.ResponseEntity<com.blue.apartamentos.models.ReservacionModel> act8_porId(
      @PathVariable Long id) {
    return service.obtenerPorId(id)
        .map(org.springframework.http.ResponseEntity::ok)
        .orElse(org.springframework.http.ResponseEntity.notFound().build());
  }

  // ACT 9: por fecha (traslape con [desde,hasta])
  @GetMapping("/actividades/por-fecha")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act9_porFecha(
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate desde,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate hasta) {
    return service.listarPorFecha(desde, hasta);
  }

  // ACT 10: por estado
  @GetMapping("/actividades/por-estado")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act10_porEstado(
      @RequestParam EstadoReservacion estado) {
    return service.listarPorEstado(estado);
  }

  // ACT 11: usuario y estado
  @GetMapping("/actividades/usuario/{idUsuario}/estado")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act11_usuarioEstado(
      @PathVariable Long idUsuario,
      @RequestParam EstadoReservacion estado) {
    return service.listarPorUsuarioYEstado(idUsuario, estado);
  }

  // ACT 12: propiedad y estado
  @GetMapping("/actividades/propiedad/{idPropiedad}/estado")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act12_propiedadEstado(
      @PathVariable Long idPropiedad,
      @RequestParam EstadoReservacion estado) {
    return service.listarPorPropiedadYEstado(idPropiedad, estado);
  }

  // ACT 13: por usuario y fecha (traslape)
  @GetMapping("/actividades/usuario/{idUsuario}/fecha")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act13_usuarioFecha(
      @PathVariable Long idUsuario,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate desde,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate hasta) {
    return service.listarPorUsuarioYFecha(idUsuario, desde, hasta);
  }

  // ACT 14: por apartamento y fecha (traslape)
  @GetMapping("/actividades/propiedad/{idPropiedad}/fecha")
  public java.util.List<com.blue.apartamentos.models.ReservacionModel> act14_propiedadFecha(
      @PathVariable Long idPropiedad,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate desde,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate hasta) {
    return service.listarPorPropiedadYFecha(idPropiedad, desde, hasta);
  }

  // ACT 15: cancelar todas por usuario (devuelve cuántas cambió)
  @PostMapping("/actividades/cancelar/usuario/{idUsuario}")
  public java.util.Map<String, Object> act15_cancelarUsuario(@PathVariable Long idUsuario) {
    int n = service.cancelarTodasPorUsuario(idUsuario);
    return java.util.Map.of("ok", true, "canceladas", n);
  }

  // ACT 16: cancelar todas por apartamento
  @PostMapping("/actividades/cancelar/propiedad/{idPropiedad}")
  public java.util.Map<String, Object> act16_cancelarPropiedad(@PathVariable Long idPropiedad) {
    int n = service.cancelarTodasPorPropiedad(idPropiedad);
    return java.util.Map.of("ok", true, "canceladas", n);
  }

  // ACT 17: cancelar todas por fecha (traslape con [desde,hasta])
  @PostMapping("/actividades/cancelar/fecha")
  public java.util.Map<String, Object> act17_cancelarFecha(
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate desde,
      @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate hasta) {
    int n = service.cancelarTodasPorFecha(desde, hasta);
    return java.util.Map.of("ok", true, "canceladas", n);
  }

}
