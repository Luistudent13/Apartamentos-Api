package com.blue.apartamentos.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blue.apartamentos.models.ResenaModel;
import com.blue.apartamentos.repositories.IResenaRepository;

@Service
@Transactional
public class ResenaService {
    private final IResenaRepository repo;
    public ResenaService(IResenaRepository repo){ this.repo = repo; }
    public ResenaModel guardar(ResenaModel r){ return repo.save(r); }
    public List<ResenaModel> porReservacion(Long id){ return repo.findByReservacion_IdReservacion(id); }
}
