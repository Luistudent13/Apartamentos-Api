package com.blue.apartamentos.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blue.apartamentos.models.PagosModel;
import com.blue.apartamentos.repositories.IPagoRepository;

@Service
@Transactional
public class PagoService {
    private final IPagoRepository repo;
    public PagoService(IPagoRepository repo){ this.repo = repo; }
    public PagosModel guardar(PagosModel p){ return repo.save(p); }
    public List<PagosModel> porReservacion(Long id){ return repo.findByReservacion_IdReservacion(id); }
}
