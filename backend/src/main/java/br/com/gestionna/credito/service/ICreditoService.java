package br.com.gestionna.credito.service;

import br.com.gestionna.credito.entity.Credito;

import java.util.List;
import java.util.Optional;

public interface ICreditoService {

    List<Credito> findByNumeroNfse(String numeroNfse);

    Optional<Credito> findByNumeroCredito(String numeroCredito);

    List<Credito> findAll();
}