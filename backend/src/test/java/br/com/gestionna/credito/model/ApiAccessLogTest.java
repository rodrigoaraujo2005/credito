package br.com.gestionna.credito.model;

import br.com.gestionna.credito.dto.LogDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ApiAccessLogTest {

    @Test
    void of_devCriarApiAccessLogComCurrentTimestamp() {
        String functionality = "testFuncinal";
        
        LogDTO apiAccessLog = LogDTO.of(functionality);
        
        assertNotNull(apiAccessLog);
        assertEquals(functionality, apiAccessLog.getFunctionality());
        assertNotNull(apiAccessLog.getAccessTime());
        LocalDateTime now = LocalDateTime.now();
        assertTrue(apiAccessLog.getAccessTime().isAfter(now.minusSeconds(1)));
        assertTrue(apiAccessLog.getAccessTime().isBefore(now.plusSeconds(1)));
    }

    @Test
    void construtorEGettersSetters_deveCorreto() {
        String functionality = "testFuncinal";
        LocalDateTime accessTime = LocalDateTime.now();
        
        LogDTO apiAccessLog = new LogDTO(functionality, accessTime);
        
        assertEquals(functionality, apiAccessLog.getFunctionality());
        assertEquals(accessTime, apiAccessLog.getAccessTime());
        
        String newFunctionality = "newFunctionality";
        LocalDateTime newAccessTime = LocalDateTime.now().plusHours(1);
        
        apiAccessLog.setFunctionality(newFunctionality);
        apiAccessLog.setAccessTime(newAccessTime);
        
        assertEquals(newFunctionality, apiAccessLog.getFunctionality());
        assertEquals(newAccessTime, apiAccessLog.getAccessTime());
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