package com.example.creditapi.service;

import com.example.creditapi.model.Credito;
import com.example.creditapi.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @InjectMocks
    private CreditoService creditoService;

    private Credito credito1;
    private Credito credito2;

    @BeforeEach
    void setUp() {
        credito1 = new Credito("123456", "7891011", LocalDate.of(2024, 2, 25),
                new BigDecimal("1500.75"), "ISSQN", true,
                new BigDecimal("5.0"), new BigDecimal("30000.00"),
                new BigDecimal("5000.00"), new BigDecimal("25000.00"));

        credito2 = new Credito("789012", "7891011", LocalDate.of(2024, 2, 26),
                new BigDecimal("1200.50"), "ISSQN", false,
                new BigDecimal("4.5"), new BigDecimal("25000.00"),
                new BigDecimal("4000.00"), new BigDecimal("21000.00"));
    }

    @Test
    void testFindByNumeroNfse() {
        // Arrange
        String numeroNfse = "7891011";
        List<Credito> expectedCreditos = Arrays.asList(credito1, credito2);
        when(creditoRepository.findByNumeroNfse(numeroNfse)).thenReturn(expectedCreditos);

        // Act
        List<Credito> result = creditoService.findByNumeroNfse(numeroNfse);

        // Assert
        assertEquals(2, result.size());
        assertEquals(expectedCreditos, result);
        verify(creditoRepository, times(1)).findByNumeroNfse(numeroNfse);
    }

    @Test
    void testFindByNumeroCredito() {
        // Arrange
        String numeroCredito = "123456";
        when(creditoRepository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(credito1));

        // Act
        Optional<Credito> result = creditoService.findByNumeroCredito(numeroCredito);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(credito1, result.get());
        verify(creditoRepository, times(1)).findByNumeroCredito(numeroCredito);
    }

    @Test
    void testFindByNumeroCreditoNotFound() {
        // Arrange
        String numeroCredito = "999999";
        when(creditoRepository.findByNumeroCredito(numeroCredito)).thenReturn(Optional.empty());

        // Act
        Optional<Credito> result = creditoService.findByNumeroCredito(numeroCredito);

        // Assert
        assertFalse(result.isPresent());
        verify(creditoRepository, times(1)).findByNumeroCredito(numeroCredito);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Credito> expectedCreditos = Arrays.asList(credito1, credito2);
        when(creditoRepository.findAll()).thenReturn(expectedCreditos);

        // Act
        List<Credito> result = creditoService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(expectedCreditos, result);
        verify(creditoRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        // Arrange
        when(creditoRepository.save(credito1)).thenReturn(credito1);

        // Act
        Credito result = creditoService.save(credito1);

        // Assert
        assertEquals(credito1, result);
        verify(creditoRepository, times(1)).save(credito1);
    }
}

