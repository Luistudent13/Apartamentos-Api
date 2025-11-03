package com.blue.apartamentos.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class MensajeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long idMensaje;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_remitente", nullable = false)
    private UsuarioModel remitente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_destinatario", nullable = false)
    private UsuarioModel destinatario;

    @ManyToOne
    @JoinColumn(name = "id_reservacion")
    private ReservacionModel reservacion;

    @Column(name = "asunto", length = 120)
    private String asunto;
    @Lob
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "leido")
    private Boolean leido = false;
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    // getters/setters…

}
