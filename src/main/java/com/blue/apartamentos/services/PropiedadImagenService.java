package com.blue.apartamentos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.PropiedadImagenModel;
import com.blue.apartamentos.repositories.IPropiedadImagenRepository;

@Service
public class PropiedadImagenService {

    @Autowired
    private IPropiedadImagenRepository repo;

    public PropiedadImagenModel save(PropiedadImagenModel img) {
        return repo.save(img);
    }

    public List<PropiedadImagenModel> listByPropiedad(Long idPropiedad) {
        return repo.findByPropiedad_IdOrderByOrdenAsc(idPropiedad);
    }
}
