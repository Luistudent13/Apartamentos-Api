package com.blue.apartamentos.services;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.UsuarioModel;
import com.blue.apartamentos.repositories.IPropiedadRepository;
import com.blue.apartamentos.repositories.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PropiedadService {

    private final IPropiedadRepository repo;
    private final IUsuarioRepository usuarioRepo;

    public PropiedadService(IPropiedadRepository repo, IUsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }

    // ---------- CRUD ----------
    public List<PropiedadModel> listar() {
        return repo.findAll();
    }

    public PropiedadModel buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public PropiedadModel crear(PropiedadModel body) {
        // Rehidratar propietario si viene solo con id
        if (body.getPropietario() != null && body.getPropietario().getIdUsuario() != null) {
            UsuarioModel u = usuarioRepo.findById(body.getPropietario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
            body.setPropietario(u);
        }
        // fechaCreacion ya la puedes tener por default en el modelo; si no, setéala
        // aquí:
        if (body.getFechaCreacion() == null)
            body.setFechaCreacion(LocalDateTime.now());
        return repo.save(body);
    }

    public PropiedadModel actualizar(Long id, PropiedadModel body) {
        PropiedadModel p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));

        // Rehidratar propietario si cambió
        if (body.getPropietario() != null && body.getPropietario().getIdUsuario() != null) {
            UsuarioModel u = usuarioRepo.findById(body.getPropietario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
            p.setPropietario(u);
        }

        // Copiar TODOS los campos del body al entity
        p.setTipo(body.getTipo());
        p.setTitulo(body.getTitulo());
        p.setDescripcion(body.getDescripcion());
        p.setDireccion(body.getDireccion());
        p.setCiudad(body.getCiudad());
        p.setCodigoPostal(body.getCodigoPostal());
        p.setPais(body.getPais());
        p.setLatitud(body.getLatitud());
        p.setLongitud(body.getLongitud());
        p.setPrecioNoche(body.getPrecioNoche());
        p.setCapacidadPersonas(body.getCapacidadPersonas());
        p.setNumeroHabitaciones(body.getNumeroHabitaciones());
        p.setNumeroBanos(body.getNumeroBanos());
        p.setMetrosCuadrados(body.getMetrosCuadrados());
        p.setComodidades(body.getComodidades());
        p.setNormasCasa(body.getNormasCasa());
        p.setEstado(body.getEstado());

        // Fecha de actualización
        p.setFechaActualizacion(LocalDateTime.now());

        return repo.save(p);
    }
}
