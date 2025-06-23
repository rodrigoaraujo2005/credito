package br.com.gestionna.credito.rest;

import br.com.gestionna.credito.dto.CreditoDTO;
import br.com.gestionna.credito.entity.Credito;
import br.com.gestionna.credito.mapper.CreditoMapper;
import br.com.gestionna.credito.service.ICreditoService;
import br.com.gestionna.credito.service.KafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditoControllerTest {

    @Mock
    private ICreditoService creditoService;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @Mock
    private CreditoMapper creditoMapper;

    @InjectMocks
    private CreditoController creditoController;

    private Credito credito;
    private CreditoDTO creditoDTO;
    private List<Credito> creditoList;
    private List<CreditoDTO> creditoDTOList;

    @BeforeEach
    void setUp() {
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
        
        creditoDTO = new CreditoDTO(
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
        
        creditoList = Arrays.asList(credito);
        creditoDTOList = Arrays.asList(creditoDTO);
    }

    @Test
    void getCreditosByNumeroNfse_deveReturnCreditos() {
        String numeroNfse = "NF456";
        when(creditoService.findByNumeroNfse(numeroNfse)).thenReturn(creditoList);
        when(creditoMapper.toDTOList(creditoList)).thenReturn(creditoDTOList);

        ResponseEntity<List<CreditoDTO>> response = creditoController.getCreditosByNumeroNfse(numeroNfse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(creditoDTOList, response.getBody());
        verify(kafkaProducerService).enviaLogNomeFuncioalidadeAcessada("getCreditosByNumeroNfse");
    }

    @Test
    void getCreditosByNumeroNfse_shouldReturnNotFoundWhenNoCreditos() {
        String numeroNfse = "naoexistente";
        when(creditoService.findByNumeroNfse(numeroNfse)).thenReturn(Collections.emptyList());

        ResponseEntity<List<CreditoDTO>> response = creditoController.getCreditosByNumeroNfse(numeroNfse);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(kafkaProducerService).enviaLogNomeFuncioalidadeAcessada("getCreditosByNumeroNfse");
    }

    @Test
    void getCreditoByNumeroCredito_deveRetornarCredito() {
        String numeroCredito = "CR123";
        when(creditoService.findByNumeroCredito(numeroCredito)).thenReturn(Optional.of(credito));
        when(creditoMapper.toDTO(credito)).thenReturn(creditoDTO);

        ResponseEntity<CreditoDTO> response = creditoController.getCreditoByNumeroCredito(numeroCredito);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(creditoDTO, response.getBody());
        verify(kafkaProducerService).enviaLogNomeFuncioalidadeAcessada("getCreditoByNumeroCredito");
    }

    @Test
    void getCreditoByNumeroCredito_deveRetornarNotFoundQuandoNoCredito() {
        String numeroCredito = "nonexistent";
        when(creditoService.findByNumeroCredito(numeroCredito)).thenReturn(Optional.empty());

        ResponseEntity<CreditoDTO> response = creditoController.getCreditoByNumeroCredito(numeroCredito);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(kafkaProducerService).enviaLogNomeFuncioalidadeAcessada("getCreditoByNumeroCredito");
    }

    @Test
    void getAllCreditos_deveRetornarAllCreditos() {
        when(creditoService.findAll()).thenReturn(creditoList);
        when(creditoMapper.toDTOList(creditoList)).thenReturn(creditoDTOList);

        ResponseEntity<List<CreditoDTO>> response = creditoController.getAllCreditos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(creditoDTOList, response.getBody());
        verify(kafkaProducerService).enviaLogNomeFuncioalidadeAcessada("getAllCreditos");
    }
}