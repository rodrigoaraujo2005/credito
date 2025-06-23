package br.com.gestionna.credito.service;

import br.com.gestionna.credito.dto.LogDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, LogDTO> kafkaTemplate;

    private KafkaProducerService kafkaProducerService;
    private final String testTopic = "test-topic";

    @BeforeEach
    void setUp() {
        kafkaProducerService = new KafkaProducerService(kafkaTemplate);
        ReflectionTestUtils.setField(kafkaProducerService, "apiAccessLogTopic", testTopic);
    }

    @Test
    void enviarApiAccessLog_deveEnviarLogToKafka() {
        LogDTO apiAccessLog = LogDTO.of("testFunctionality");

        kafkaProducerService.sendApiAccessLog(apiAccessLog);

        verify(kafkaTemplate).send(eq(testTopic), eq(apiAccessLog));
    }

    @Test
    void logApiAccess_deveCriarEEnviarLogToKafka() {
        String functionality = "testFunctionality";

        kafkaProducerService.logApiAccess(functionality);

        ArgumentCaptor<LogDTO> logCaptor = ArgumentCaptor.forClass(LogDTO.class);
        verify(kafkaTemplate).send(eq(testTopic), logCaptor.capture());

        LogDTO capturedLog = logCaptor.getValue();
        assertNotNull(capturedLog);
        assertEquals(functionality, capturedLog.getFunctionality());
        assertNotNull(capturedLog.getAccessTime());
    }
}