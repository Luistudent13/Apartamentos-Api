package com.blue.apartamentos.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="reseñas") // si tu tabla se llama "resenas", cambia el name
public class ResenaModel {

  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id_resena") private Long idResena;

  @ManyToOne(optional=false) @JoinColumn(name="id_reservacion", nullable=false)
  private ReservacionModel reservacion;

  @Column(name="calificacion_limpieza")     private Integer calificacionLimpieza;
  @Column(name="calificacion_ubicacion")    private Integer calificacionUbicacion;
  @Column(name="calificacion_comunicacion") private Integer calificacionComunicacion;
  @Column(name="calificacion_general")      private Integer calificacionGeneral;

  @Lob @Column(name="comentario") private String comentario;
  @Column(name="fecha_resena") private LocalDateTime fechaResena = LocalDateTime.now();
  @Lob @Column(name="respuesta_propietario") private String respuestaPropietario;
  @Column(name="fecha_respuesta") private LocalDateTime fechaRespuesta;

  // getters/setters…
}
