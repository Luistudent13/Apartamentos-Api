package com.blue.apartamentos.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "propiedades")
public class PropiedadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Long idPropiedad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_propietario", nullable = false)
    private UsuarioModel propietario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoPropiedad tipo = TipoPropiedad.DEPARTAMENTO;

    @Column(name = "titulo", length = 120)
    private String titulo;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ciudad", length = 80)
    private String ciudad;
    @Column(name = "codigo_postal", length = 20)
    private String codigoPostal;
    @Column(name = "pais", length = 60)
    private String pais;

    @Column(name = "latitud", precision = 10, scale = 6)
    private BigDecimal latitud;
    @Column(name = "longitud", precision = 10, scale = 6)
    private BigDecimal longitud;

    @Column(name = "precio_noche", precision = 12, scale = 2)
    private BigDecimal precioNoche;
    @Column(name = "capacidad_personas")
    private Integer capacidadPersonas;
    @Column(name = "numero_habitaciones")
    private Integer numeroHabitaciones;
    @Column(name = "numero_banos")
    private Integer numeroBanos;
    @Column(name = "metros_cuadrados")
    private Integer metrosCuadrados;

    @Lob
    @Column(name = "comodidades")
    private String comodidades;
    @Lob
    @Column(name = "normas_casa")
    private String normasCasa;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPropiedad estado = EstadoPropiedad.PUBLICADA;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropiedadImagenModel> imagenes;

    @OneToMany(mappedBy = "propiedad")
    private List<DisponibilidadModel> disponibilidades;
    @OneToMany(mappedBy = "propiedad")
    private List<ReservacionModel> reservaciones;

    // Getters/Setters
    public Long getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Long idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public UsuarioModel getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioModel propietario) {
        this.propietario = propietario;
    }

    public TipoPropiedad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPropiedad tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(BigDecimal precioNoche) {
        this.precioNoche = precioNoche;
    }

    public Integer getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(Integer capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public Integer getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(Integer numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public Integer getNumeroBanos() {
        return numeroBanos;
    }

    public void setNumeroBanos(Integer numeroBanos) {
        this.numeroBanos = numeroBanos;
    }

    public Integer getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(Integer metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public String getNormasCasa() {
        return normasCasa;
    }

    public void setNormasCasa(String normasCasa) {
        this.normasCasa = normasCasa;
    }

    public EstadoPropiedad getEstado() {
        return estado;
    }

    public void setEstado(EstadoPropiedad estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public enum TipoPropiedad {
        CASA, DEPARTAMENTO, ESTUDIO, HABITACION
    }

    public enum EstadoPropiedad {
        PUBLICADA, OCULTA
    }
}

/*
 POST http://localhost:10101/api/propiedades

 {
  "propietario": {
    "idUsuario": 1
  },
  "tipo": "DEPARTAMENTO",
  "titulo": "Departamento en Veracruz",
  "descripcion": "Hermoso departamento con vista al mar.",
  "direccion": "Blvd. Avila Camacho #1000, Boca del Río",
  "ciudad": "Veracruz",
  "codigoPostal": "94290",
  "pais": "México",
  "latitud": 19.191,
  "longitud": -96.134,
  "precioNoche": 1200.00,
  "capacidadPersonas": 4,
  "numeroHabitaciones": 2,
  "numeroBanos": 1,
  "metrosCuadrados": 80,
  "comodidades": "WiFi, Aire acondicionado, TV",
  "normasCasa": "No mascotas, no fumar",
  "estado": "PUBLICADA"
}
PUT 
{
  "idPropiedad": 1,
  "propietario": {
    "idUsuario": 1
  },
  "tipo": "HABITACION",
  "titulo": "Depto remodelado con alberca",
  "descripcion": "Departamento remodelado con vista al mar y alberca.",
  "direccion": "Blvd. Ruiz Camacho #16500, Boca del Río",
  "ciudad": "Xalapa",
  "codigoPostal": "55590",
  "pais": "Panama",
  "latitud": 14.351,
  "longitud": -10.134,
  "precioNoche": 1500.00,
  "capacidadPersonas": 9,
  "numeroHabitaciones": 9,
  "numeroBanos": 2,
  "metrosCuadrados": 90,
  "comodidades": "WiFi, Aire acondicionado, TV, Alberca",
  "normasCasa": "No mascotas, no fumar, no comer",
  "estado": "OCULTA"
}
 */