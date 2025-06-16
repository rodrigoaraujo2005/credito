package br.com.gestionna.credito.service;


import br.com.gestionna.credito.entity.Credito;
import br.com.gestionna.credito.repository.CreditoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CreditoService {
    
    private final CreditoRepository creditoRepository;
    
    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse);
    }
    
    public Optional<Credito> findByNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito);
    }
    
    public List<Credito> findAll() {
        return creditoRepository.findAll();
    }
    
    public Credito save(Credito credito) {
        return creditoRepository.save(credito);
    }
}

