package com.blue.apartamentos.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "propiedad_imagenes")
public class PropiedadImagenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_propiedad", nullable = false)
    private PropiedadModel propiedad;

    @Column(name = "url_imagen", nullable = false)
    private String urlImagen;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "es_principal")
    private Boolean esPrincipal = false;
    @Column(name = "fecha_subida")
    private LocalDateTime fechaSubida = LocalDateTime.now();

    // getters/setters
    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    public PropiedadModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadModel propiedad) {
        this.propiedad = propiedad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
/*
POST http://localhost:10101/propiedad-imagenes
 {
  "propiedad": {
    "idPropiedad": 1
  },
  "urlImagen": "https://mi-servidor.com/imagenes/apartamento1_fachada.jpg",
  "orden": 1,
  "esPrincipal": true,
  "fechaSubida": "2025-11-02T18:45:00"
}


PUT http://localhost:10101/propiedad-imagenes/1


 */