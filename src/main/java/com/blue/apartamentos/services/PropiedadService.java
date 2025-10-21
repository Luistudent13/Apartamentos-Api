package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.repositories.IPropiedadRepository;

@Service
public class PropiedadService {

    @Autowired
    private IPropiedadRepository propiedadRepository;

    public List<PropiedadModel> getAllPropiedades() {
        return propiedadRepository.findAll();
    }

    public Optional<PropiedadModel> getPropiedadById(Long id) {
        return propiedadRepository.findById(id);
    }

    public PropiedadModel savePropiedad(PropiedadModel propiedad) {
        return propiedadRepository.save(propiedad);
    }

    public PropiedadModel updatePropiedad(Long id, PropiedadModel propiedadDetalle) {
        return propiedadRepository.findById(id).map(propiedad -> {
            propiedad.setTitulo(propiedadDetalle.getTitulo());
            propiedad.setDescripcion(propiedadDetalle.getDescripcion());
            propiedad.setDireccion(propiedadDetalle.getDireccion());
            propiedad.setPrecioNoche(propiedadDetalle.getPrecioNoche());
            propiedad.setTipo(propiedadDetalle.getTipo());
            propiedad.setPropietario(propiedadDetalle.getPropietario());
            return propiedadRepository.save(propiedad);
        }).orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada"));
    }

    public void deletePropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }

    public List<PropiedadModel> getPropiedadesByTipo(PropiedadModel.TipoPropiedad tipo) {
        return propiedadRepository.findByTipo(tipo);
    }

    public List<PropiedadModel> getPropiedadesByPropietario(Long idPropietario) {
        return propiedadRepository.findByPropietarioId(idPropietario);
    }
}
