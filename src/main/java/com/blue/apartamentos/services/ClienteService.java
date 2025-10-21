package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.repositories.IClienteRepository;

@Service
public class ClienteService {

    private final IClienteRepository repo;

    public ClienteService(IClienteRepository repo) {
        this.repo = repo;
    }

    public List<ClienteModel> getAllClientes() {
        return repo.findAll();
    }

    public Optional<ClienteModel> getClienteById(Long id) {
        return repo.findById(id);
    }

    public ClienteModel saveCliente(ClienteModel c) {
    System.out.println("DEBUG password = " + c.getPassword()); // <- TEMPORAL
    return repo.save(c);
}

    public Optional<ClienteModel> updateCliente(Long id, ClienteModel changes) {
    return repo.findById(id).map(existing -> {
        if (changes.getTipo() != null) existing.setTipo(changes.getTipo());
        if (changes.getNombres() != null) existing.setNombres(changes.getNombres());
        if (changes.getApellidos() != null) existing.setApellidos(changes.getApellidos());
        if (changes.getEmail() != null) existing.setEmail(changes.getEmail());
        if (changes.getTelefono() != null) existing.setTelefono(changes.getTelefono());
        if (changes.getFechaNacimiento() != null) existing.setFechaNacimiento(changes.getFechaNacimiento());
        if (changes.getIne() != null) existing.setIne(changes.getIne());
        if (changes.getDireccion() != null) existing.setDireccion(changes.getDireccion());
        if (changes.getPassword() != null) existing.setPassword(changes.getPassword());
        if (changes.getEstatus() != null) existing.setEstatus(changes.getEstatus());
        if (changes.getFechaRegistro() != null) existing.setFechaRegistro(changes.getFechaRegistro()); // <- aquí el nombre correcto
        return repo.save(existing);
    });
}

    public void deleteCliente(Long id) {
        repo.deleteById(id);
    }
}
