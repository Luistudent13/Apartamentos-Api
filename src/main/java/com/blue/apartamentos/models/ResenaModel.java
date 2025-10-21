package com.blue.apartamentos.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "resenas")
public class ResenaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private ReservacionModel reservacion;

    @Column(name = "calificacion_general")
    private Integer calificacion_general;

    @Column(name = "calificacion_limpieza")
    private Integer calificacion_limpieza;

    @Column(name = "calificacion_ubicacion")
    private Integer calificacion_ubicacion;

    @Column(name = "calificacion_comunicacion")
    private Integer calificacion_comunicacion;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_resena", nullable = false)
    private LocalDateTime fecha_resena = LocalDateTime.now();

    @Column(name = "respuesta_propietario", columnDefinition = "TEXT")
    private String respuesta_propietario;

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservacionModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservacionModel reservacion) {
        this.reservacion = reservacion;
    }

    public Integer getCalificacion_general() {
        return calificacion_general;
    }

    public void setCalificacion_general(Integer calificacion_general) {
        this.calificacion_general = calificacion_general;
    }

    public Integer getCalificacion_limpieza() {
        return calificacion_limpieza;
    }

    public void setCalificacion_limpieza(Integer calificacion_limpieza) {
        this.calificacion_limpieza = calificacion_limpieza;
    }

    public Integer getCalificacion_ubicacion() {
        return calificacion_ubicacion;
    }

    public void setCalificacion_ubicacion(Integer calificacion_ubicacion) {
        this.calificacion_ubicacion = calificacion_ubicacion;
    }

    public Integer getCalificacion_comunicacion() {
        return calificacion_comunicacion;
    }

    public void setCalificacion_comunicacion(Integer calificacion_comunicacion) {
        this.calificacion_comunicacion = calificacion_comunicacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFecha_resena() {
        return fecha_resena;
    }

    public void setFecha_resena(LocalDateTime fecha_resena) {
        this.fecha_resena = fecha_resena;
    }

    public String getRespuesta_propietario() {
        return respuesta_propietario;
    }

    public void setRespuesta_propietario(String respuesta_propietario) {
        this.respuesta_propietario = respuesta_propietario;
    }
}
/*{
  "reservacion": { "id_reservacion": 1 },
  "calificacion": 5,
  "comentario": "Excelente lugar, limpio y con buena vista",
  "fecha": "2025-11-12T18:00:00"
}
 */