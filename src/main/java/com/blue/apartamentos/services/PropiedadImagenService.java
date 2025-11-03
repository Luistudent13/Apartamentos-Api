package com.blue.apartamentos.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blue.apartamentos.models.PropiedadImagenModel;
import com.blue.apartamentos.repositories.IPropiedadImagenRepository;

@Service
@Transactional
public class PropiedadImagenService {
    private final IPropiedadImagenRepository repo;
    public PropiedadImagenService(IPropiedadImagenRepository repo){ this.repo = repo; }

    public List<PropiedadImagenModel> porPropiedad(Long idPropiedad){
        return repo.findByPropiedad_IdPropiedadOrderByOrdenAsc(idPropiedad);
    }
    public PropiedadImagenModel guardar(PropiedadImagenModel m){ return repo.save(m); }
    public void eliminar(Long id){ repo.deleteById(id); }
}
