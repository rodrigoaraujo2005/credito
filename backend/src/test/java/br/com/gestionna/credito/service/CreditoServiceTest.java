package br.com.gestionna.credito.service;

import br.com.gestionna.credito.entity.Credito;
import br.com.gestionna.credito.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    private CreditoService creditoService;
    private Credito credito;

    @BeforeEach
    void setUp() {
        creditoService = new CreditoService(creditoRepository);
        
        credito = new Credito(
                1L,
                "CR123",
                "NF456",
                LocalDate.of(2023, 1, 1),
                new BigDecimal("100.00"),
                "ISSQN",
                true,
                new BigDecimal("5.00"),
                new BigDecimal("2000.00"),
                new BigDecimal("0.00"),
                new BigDecimal("2000.00")
        );
    }

    @Test
    void findByNumeroNfse_deveRetornarCreditos() {
        String numeroNfse = "NF456";
        when(creditoRepository.findByNumeroNfse(numeroNfse)).thenReturn(Arrays.asList(credito));

        List<Credito> result = creditoService.findByNumeroNfse(numeroNfse);

        assertEquals(1, result.size());
        assertEquals(credito, result.get(0));
    }

    @Test
    void findByNumeroNfse_deveRetornarEmptyListQuandoNoCreditos() {
        String numeroNfse = "nonexistent";
        when(creditoRepository.findByNumeroNfse(numeroNfse)).thenReturn(Collections.emptyList());

        List<Credito> result = creditoService.findByNumeroNfse(numeroNfse);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByNumeroCredito_deveRetornarCredito() {
        String numeroCredito = "CR123";
        when(creditoRepository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(credito));

        Optional<Credito> result = creditoService.findByNumeroCredito(numeroCredito);

        assertTrue(result.isPresent());
        assertEquals(credito, result.get());
    }

    @Test
    void findByNumeroCredito_deveRetornarEmptyOptionalQuandoNoCredito() {
        String numeroCredito = "nonexistent";
        when(creditoRepository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.empty());

        Optional<Credito> result = creditoService.findByNumeroCredito(numeroCredito);

        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_deveRetornarAllCreditos() {
        when(creditoRepository.findAll()).thenReturn(Arrays.asList(credito));

        List<Credito> result = creditoService.findAll();

        assertEquals(1, result.size());
        assertEquals(credito, result.get(0));
    }

    @Test
    void findAll_deveRetornarEmptyListQuandoNoCreditos() {
        when(creditoRepository.findAll()).thenReturn(Collections.emptyList());

        List<Credito> result = creditoService.findAll();

        assertTrue(result.isEmpty());
    }
}