package com.blue.apartamentos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.PrePersist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ClienteModel {

    public enum TipoCliente {
        INDIVIDUAL, EMPRESA
    }

    public enum Estatus {
        ACTIVO, INACTIVO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipo = TipoCliente.INDIVIDUAL;

    @Column(name = "nombre", nullable = false, length = 35)
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "numero_ine", unique = true, length = 20)
    private String ine;

    @Column(name = "direccion")
    private String direccion;

    // 👇 visible en JSON para tu práctica
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus")
    private Estatus estatus;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    private void prePersist() {
        if (fechaRegistro == null)
            fechaRegistro = LocalDateTime.now();
        if (estatus == null)
            estatus = Estatus.ACTIVO;
    }

    // ===== Getters/Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() { // <- asegúrate de que este existe
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIne() {
        return ine;
    }

    public void setIne(String ine) {
        this.ine = ine;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() { // <- AÑADE ESTO si no lo tenías
        return password;
    }

    public void setPassword(String password) { // <- AÑADE ESTO si no lo tenías
        this.password = password;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public LocalDateTime getFechaRegistro() { // <- y este también
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}


/*{
  "tipo": "EMPRESA",
  "nombres": "TechNova Solutions S.A. de C.V.",
  "apellidos": "Sucursal Veracruz",
  "email": "contacto@technova.mx",
  "telefono": "2296543210",
  "fechaNacimiento": "2010-05-12",
  "ine": "EMP1234567890MX",
  "direccion": "Av. 5 de Mayo #150, Col. Centro, Veracruz, Ver.",
  "password": "ClaveEmpresa2025"
}

{
  "tipo": "INDIVIDUAL",
  "nombres": "Luis Fernando",
  "apellidos": "Aldama Castro",
  "email": "luis.aldama@example.com",
  "telefono": "2299876543",
  "fechaNacimiento": "1998-04-12",
  "ine": "IND8765432100MX",
  "direccion": "Calle Los Pinos #45, Fracc. Reforma, Veracruz, Ver.",
  "password": "ClaveIndividual2025"
}

{
  "tipo": "INDIVIDUAL",
  "nombres": "Luis Fernando",
  "apellidos": "Aldama Castro",
  "email": "luis@example.com",
  "telefono": "2291234567",
  "fechaNacimiento": "2001-10-21",
  "ine": "LFA123456789",
  "direccion": "Veracruz Centro",
  "password": "123456",
  "estatus": "ACTIVO"
}

*/