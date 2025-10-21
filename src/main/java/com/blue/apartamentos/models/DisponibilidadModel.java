package com.blue.apartamentos.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "disponibilidades")
public class DisponibilidadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private PropiedadModel propiedad;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

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

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Alias
    @Deprecated
    public LocalDateTime getFecha_inicio() {
        return fechaInicio;
    }

    @Deprecated
    public void setFecha_inicio(LocalDateTime v) {
        this.fechaInicio = v;
    }

    @Deprecated
    public LocalDateTime getFecha_fin() {
        return fechaFin;
    }

    @Deprecated
    public void setFecha_fin(LocalDateTime v) {
        this.fechaFin = v;
    }
}

/*{
  "propiedad": { "id_propiedad": 1 },
  "fechaInicio": "2025-11-01T00:00:00",
  "fechaFin": "2025-11-15T23:59:59",
  "disponible": true
}
 */