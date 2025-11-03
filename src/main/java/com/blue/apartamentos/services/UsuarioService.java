package com.blue.apartamentos.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.blue.apartamentos.repositories.IUsuarioRepository;
import com.blue.apartamentos.models.UsuarioModel;

@Service
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // ---- CRUD básico ----

    public List<UsuarioModel> listar() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscar(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public UsuarioModel crear(UsuarioModel usuario) {
        // Si quieres, puedes inicializar fechaRegistro aquí, pero ya la traes por default en el modelo
        return usuarioRepository.save(usuario);
    }

    /**
     * PUT: actualiza TODOS los campos que mandes en el body.
     * Además, SOLO en PUT, setea ultimoLogin = ahora (según lo que pediste).
     */
    public UsuarioModel actualizar(Long id, UsuarioModel body) {
        UsuarioModel u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualiza TODO (respetando los nombres exactos de tu modelo):
        u.setTipo(body.getTipo());                           // INDIVIDUAL | EMPRESA
        u.setNombre(body.getNombre());
        u.setApellido(body.getApellido());
        u.setEmail(body.getEmail());
        u.setTelefono(body.getTelefono());
        u.setFechaNacimiento(body.getFechaNacimiento());
        u.setDocumentoIdentidad(body.getDocumentoIdentidad());
        u.setDireccion(body.getDireccion());
        u.setContrasenaHash(body.getContrasenaHash());
        u.setEstado(body.getEstado());                       // ACTIVO | INACTIVO

        // Solo cuando es PUT: ultimoLogin = ahora
        u.setUltimoLogin(LocalDateTime.now());

        return usuarioRepository.save(u);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
