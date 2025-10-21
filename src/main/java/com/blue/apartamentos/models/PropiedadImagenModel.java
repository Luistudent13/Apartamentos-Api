package com.blue.apartamentos.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "propiedad_imagenes")
public class PropiedadImagenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private PropiedadModel propiedad;

    @Column(name = "url_imagen", nullable = false, length = 500)
    private String url_imagen;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "es_principal")
    private Boolean es_principal = Boolean.FALSE;

    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fecha_subida = LocalDateTime.now();

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

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEs_principal() {
        return es_principal;
    }

    public void setEs_principal(Boolean es_principal) {
        this.es_principal = es_principal;
    }

    public LocalDateTime getFecha_subida() {
        return fecha_subida;
    }

    public void setFecha_subida(LocalDateTime fecha_subida) {
        this.fecha_subida = fecha_subida;
    }
}
