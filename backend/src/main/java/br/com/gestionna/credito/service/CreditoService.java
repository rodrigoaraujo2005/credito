package br.com.gestionna.credito.service;

import br.com.gestionna.credito.entity.Credito;
import br.com.gestionna.credito.repository.CreditoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class CreditoService implements ICreditoService {

    private final CreditoRepository creditoRepository;

    @Override
    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse);
    }

    @Override
    public Optional<Credito> findByNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito);
    }

    @Override
    public List<Credito> findAll() {
        return creditoRepository.findAll();
    }

}
