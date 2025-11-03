package com.blue.apartamentos.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUsuario tipo = TipoUsuario.INDIVIDUAL;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "apellido", length = 100)
    private String apellido;
    @Column(name = "email", length = 120)
    private String email;
    @Column(name = "telefono", length = 30)
    private String telefono;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "documento_identidad", length = 50)
    private String documentoIdentidad;
    @Lob
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

    // Simple, como pediste
    @Column(name = "contrasena_hash", length = 255)
    private String contrasenaHash;
    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    // Relaciones
    @OneToMany(mappedBy = "propietario")
    private List<PropiedadModel> propiedades;
    @OneToMany(mappedBy = "usuario")
    private List<ReservacionModel> reservaciones;

    // Getters/Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public enum TipoUsuario {
        INDIVIDUAL, EMPRESA
    }

    public enum EstadoUsuario {
        ACTIVO, INACTIVO
    }

    public static boolean isPresent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPresent'");
    }
}


/*
//Crear usuario
POST http://localhost:10101/api/usuarios
{
  "tipo": "INDIVIDUAL",
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan.perez@example.com",
  "telefono": "2291234567",
  "fechaNacimiento": "1998-05-10",
  "documentoIdentidad": "INE-ABC123",
  "direccion": "Av. Siempre Viva 123, Veracruz",
  "contrasenaHash": "123456",
  "estado": "ACTIVO"
}
//obtener todos
GET http://localhost:10101/api/usuarios
//por usuario
GET http://localhost:10101/api/usuarios/1


PUT http://localhost:10101/api/usuarios/1
//Actualizar usuario
{
  "idUsuario": 1,
  "tipo": "EMPRESA",
  "nombre": "Juan Actualizadro",
  "apellido": "Pérez act",
  "email": "juan.perezact@example.com",
  "telefono": "2297655555",
  "fechaNacimiento": "2000-05-10",
  "documentoIdentidad": "INE-ABC890",
  "direccion": "Calle 2 #456, Veracruz",
  "contrasenaHash": "666666",
  "estado": "INACTIVO"
}

DELETE http://localhost:10101/api/usuarios/1

*/