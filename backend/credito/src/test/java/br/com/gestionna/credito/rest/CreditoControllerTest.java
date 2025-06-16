package br.com.gestionna.credito.rest;

import br.com.gestionna.credito.entity.Credito;
import br.com.gestionna.credito.service.CreditoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditoController.class)
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    private CreditoService creditoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Credito credito1;
    private Credito credito2;

    @BeforeEach
    void setUp() {
     /*   credito1 = new Credito("123456", "7891011", LocalDate.of(2024, 2, 25),
                new BigDecimal("1500.75"), "ISSQN", true,
                new BigDecimal("5.0"), new BigDecimal("30000.00"),
                new BigDecimal("5000.00"), new BigDecimal("25000.00"));

        credito2 = new Credito("789012", "7891011", LocalDate.of(2024, 2, 26),
                new BigDecimal("1200.50"), "ISSQN", false,
                new BigDecimal("4.5"), new BigDecimal("25000.00"),
                new BigDecimal("4000.00"), new BigDecimal("21000.00")); */
    }

    @Test
    void testGetCreditosByNumeroNfse() throws Exception {
        // Arrange
        String numeroNfse = "7891011";
        List<Credito> creditos = Arrays.asList(credito1, credito2);
        when(creditoService.findByNumeroNfse(numeroNfse)).thenReturn(creditos);

        // Act & Assert
        mockMvc.perform(get("/api/creditos/{numeroNfse}", numeroNfse))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].numeroCredito").value("123456"))
                .andExpect(jsonPath("$[1].numeroCredito").value("789012"));

        verify(creditoService, times(1)).findByNumeroNfse(numeroNfse);
    }

    @Test
    void testGetCreditosByNumeroNfseNotFound() throws Exception {
        // Arrange
        String numeroNfse = "999999";
        when(creditoService.findByNumeroNfse(numeroNfse)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/creditos/{numeroNfse}", numeroNfse))
                .andExpect(status().isNotFound());

        verify(creditoService, times(1)).findByNumeroNfse(numeroNfse);
    }

    @Test
    void testGetCreditoByNumeroCredito() throws Exception {
        // Arrange
        String numeroCredito = "123456";
        when(creditoService.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(credito1));

        // Act & Assert
        mockMvc.perform(get("/api/creditos/credito/{numeroCredito}", numeroCredito))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numeroCredito").value("123456"))
                .andExpect(jsonPath("$.numeroNfse").value("7891011"));

        verify(creditoService, times(1)).findByNumeroCredito(numeroCredito);
    }

    @Test
    void testGetCreditoByNumeroCreditoNotFound() throws Exception {
        // Arrange
        String numeroCredito = "999999";
        when(creditoService.findByNumeroCredito(numeroCredito)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/creditos/credito/{numeroCredito}", numeroCredito))
                .andExpect(status().isNotFound());

        verify(creditoService, times(1)).findByNumeroCredito(numeroCredito);
    }

    @Test
    void testGetAllCreditos() throws Exception {
        // Arrange
        List<Credito> creditos = Arrays.asList(credito1, credito2);
        when(creditoService.findAll()).thenReturn(creditos);

        // Act & Assert
        mockMvc.perform(get("/api/creditos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        verify(creditoService, times(1)).findAll();
    }
}

