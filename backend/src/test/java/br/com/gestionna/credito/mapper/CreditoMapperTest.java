package br.com.gestionna.credito.mapper;

import br.com.gestionna.credito.dto.CreditoDTO;
import br.com.gestionna.credito.entity.Credito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditoMapperTest {

    private CreditoMapper creditoMapper;
    private Credito credito;
    private CreditoDTO creditoDTO;

    @BeforeEach
    void setUp() {
        creditoMapper = new CreditoMapper();
        
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
                2L,
                "CR789",
                "NF012",
                LocalDate.of(2023, 2, 1),
                new BigDecimal("200.00"),
                "ISSQN",
                false,
                new BigDecimal("10.00"),
                new BigDecimal("2000.00"),
                new BigDecimal("0.00"),
                new BigDecimal("2000.00")
        );
    }

    @Test
    void toDTO_deveConverterEntityEmDTO() {
        CreditoDTO result = creditoMapper.toDTO(credito);
        assertNotNull(result);
        assertEquals(credito.getId(), result.getId());
        assertEquals(credito.getNumeroCredito(), result.getNumeroCredito());
        assertEquals(credito.getNumeroNfse(), result.getNumeroNfse());
        assertEquals(credito.getDataConstituicao(), result.getDataConstituicao());
        assertEquals(credito.getValorIssqn(), result.getValorIssqn());
        assertEquals(credito.getTipoCredito(), result.getTipoCredito());
        assertEquals(credito.getSimplesNacional(), result.getSimplesNacional());
        assertEquals(credito.getAliquota(), result.getAliquota());
        assertEquals(credito.getValorFaturado(), result.getValorFaturado());
        assertEquals(credito.getValorDeducao(), result.getValorDeducao());
        assertEquals(credito.getBaseCalculo(), result.getBaseCalculo());
    }

    @Test
    void toDTO_deveRetornoNullQuandoEntityIsNull() {
        CreditoDTO result = creditoMapper.toDTO(null);
        assertNull(result);
    }

    @Test
    void toEntity_deveConverterDTOEmEntity() {
        Credito result = creditoMapper.toEntity(creditoDTO);
        
        assertNotNull(result);
        assertEquals(creditoDTO.getId(), result.getId());
        assertEquals(creditoDTO.getNumeroCredito(), result.getNumeroCredito());
        assertEquals(creditoDTO.getNumeroNfse(), result.getNumeroNfse());
        assertEquals(creditoDTO.getDataConstituicao(), result.getDataConstituicao());
        assertEquals(creditoDTO.getValorIssqn(), result.getValorIssqn());
        assertEquals(creditoDTO.getTipoCredito(), result.getTipoCredito());
        assertEquals(creditoDTO.getSimplesNacional(), result.getSimplesNacional());
        assertEquals(creditoDTO.getAliquota(), result.getAliquota());
        assertEquals(creditoDTO.getValorFaturado(), result.getValorFaturado());
        assertEquals(creditoDTO.getValorDeducao(), result.getValorDeducao());
        assertEquals(creditoDTO.getBaseCalculo(), result.getBaseCalculo());
    }

    @Test
    void toEntity_deveRetornarNullQuandoDTOIsNull() {
        Credito result = creditoMapper.toEntity(null);
        assertNull(result);
    }

    @Test
    void toDTOList_deveConverterEntityListParaDTOList() {
        List<Credito> entities = Arrays.asList(credito);
        
        List<CreditoDTO> results = creditoMapper.toDTOList(entities);
        
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(credito.getId(), results.get(0).getId());
        assertEquals(credito.getNumeroCredito(), results.get(0).getNumeroCredito());
    }

    @Test
    void toDTOList_shouldReturnNullWhenEntityListIsNull() {
        List<CreditoDTO> results = creditoMapper.toDTOList(null);
        assertNull(results);
    }

    @Test
    void toEntityList_deveConverterDTOListEmEntityList() {
        List<CreditoDTO> dtos = Arrays.asList(creditoDTO);
        
        List<Credito> results = creditoMapper.toEntityList(dtos);
        
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(creditoDTO.getId(), results.get(0).getId());
        assertEquals(creditoDTO.getNumeroCredito(), results.get(0).getNumeroCredito());
    }

    @Test
    void toEntityList_deveRetornarNullQuandoDTOListIsNull() {
        List<Credito> results = creditoMapper.toEntityList(null);
        
        assertNull(results);
    }
}