package com.blue.apartamentos.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "mensajes")
public class MensajeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_remitente", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private ClienteModel remitente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destinatario", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private ClienteModel destinatario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private ReservacionModel reservacion;

    @Column(name = "contenido", columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getRemitente() {
        return remitente;
    }

    public void setRemitente(ClienteModel remitente) {
        this.remitente = remitente;
    }

    public ClienteModel getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(ClienteModel destinatario) {
        this.destinatario = destinatario;
    }

    public ReservacionModel getReservacion() {
        return reservacion;
    }

    public void setReservacion(ReservacionModel reservacion) {
        this.reservacion = reservacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    // Alias JSON antiguos
    @Deprecated
    @JsonProperty("fecha_envio")
    public LocalDateTime getFecha_envio() {
        return fechaEnvio;
    }

    @Deprecated
    @JsonProperty("fecha_envio")
    public void setFecha_envio(LocalDateTime v) {
        this.fechaEnvio = v;
    }
}
