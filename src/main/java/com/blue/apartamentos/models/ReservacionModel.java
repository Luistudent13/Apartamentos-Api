package com.blue.apartamentos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "reservaciones")
public class ReservacionModel {

    public enum EstadoReservacion {
        PENDIENTE, CONFIRMADA, CANCELADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private PropiedadModel propiedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private ClienteModel cliente;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoReservacion estado = EstadoReservacion.PENDIENTE;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropiedadModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadModel propiedad) {
        this.propiedad = propiedad;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public EstadoReservacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoReservacion estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // Alias para compatibilidad con nombres viejos
    @Deprecated
    public Long getId_reservacion() {
        return id;
    }

    @Deprecated
    public void setId_reservacion(Long v) {
        this.id = v;
    }

    @Deprecated
    public LocalDate getFecha_entrada() {
        return fechaEntrada;
    }

    @Deprecated
    public void setFecha_entrada(LocalDate v) {
        this.fechaEntrada = v;
    }

    @Deprecated
    public LocalDate getFecha_salida() {
        return fechaSalida;
    }

    @Deprecated
    public void setFecha_salida(LocalDate v) {
        this.fechaSalida = v;
    }
}

/*{
  "cliente": { "id": 1 },
  "propiedad": { "id_propiedad": 1 },
  "fechaEntrada": "2025-11-05T15:00:00",
  "fechaSalida": "2025-11-10T11:00:00",
  "estado": "CONFIRMADA",
  "total": 4750.0
}
 */