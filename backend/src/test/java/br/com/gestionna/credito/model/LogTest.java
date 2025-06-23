package br.com.gestionna.credito.model;

import br.com.gestionna.credito.dto.LogDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @Test
    void of_devCriarApiAccessLogComCurrentTimestamp() {
        String funcionalidade = "testFuncinal";
        
        LogDTO log = LogDTO.of(funcionalidade);
        
        assertNotNull(log);
        assertEquals(funcionalidade, log.getFuncionalidade());
        assertNotNull(log.getDataHoraAcesso());
        LocalDateTime now = LocalDateTime.now();
        assertTrue(log.getDataHoraAcesso().isAfter(now.minusSeconds(1)));
        assertTrue(log.getDataHoraAcesso().isBefore(now.plusSeconds(1)));
    }

    @Test
    void construtorEGettersSetters_deveCorreto() {
        String funcionalidade = "testFuncinal";
        LocalDateTime accessTime = LocalDateTime.now();
        
        LogDTO log = new LogDTO(funcionalidade, accessTime);
        
        assertEquals(funcionalidade, log.getFuncionalidade());
        assertEquals(accessTime, log.getDataHoraAcesso());
        
        String newFunctionality = "newFunctionality";
        LocalDateTime newAccessTime = LocalDateTime.now().plusHours(1);
        
        log.setFuncionalidade(newFunctionality);
        log.setDataHoraAcesso(newAccessTime);
        
        assertEquals(newFunctionality, log.getFuncionalidade());
        assertEquals(newAccessTime, log.getDataHoraAcesso());
    }

    @Test
    void equalsAndHashCode_deveCorreto() {
        String functionality = "testFuncinal";
        LocalDateTime accessTime = LocalDateTime.now();
        
        LogDTO apiAccessLog1 = new LogDTO(functionality, accessTime);
        LogDTO apiAccessLog2 = new LogDTO(functionality, accessTime);
        LogDTO apiAccessLog3 = new LogDTO("testDiferenceFuncinal", accessTime);
        
        // Then
        assertEquals(apiAccessLog1, apiAccessLog2);
        assertEquals(apiAccessLog1.hashCode(), apiAccessLog2.hashCode());
        
        assertNotEquals(apiAccessLog1, apiAccessLog3);
        assertNotEquals(apiAccessLog1.hashCode(), apiAccessLog3.hashCode());
    }

    @Test
    void toString_deveRetornarStringRepresentacaoDoObjeto() {
        String functionality = "testFuncinal";
        LocalDateTime accessTime = LocalDateTime.now();
        LogDTO apiAccessLog = new LogDTO(functionality, accessTime);
        
        String result = apiAccessLog.toString();
        
        assertNotNull(result);
        assertTrue(result.contains(functionality));
        assertTrue(result.contains(accessTime.toString()));
    }
}