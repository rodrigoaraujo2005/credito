package br.com.gestionna.credito.service;

import br.com.gestionna.credito.dto.LogDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, LogDTO> kafkaTemplate;
    
    @Value("${app.kafka.topic.api-access-log}")
    private String logTopic;
    

    public void enviaLog(LogDTO logs) {
        log.debug("Enviando log para o Kafka: {}", logs);
        kafkaTemplate.send(logTopic, logs);
    }
    

    public void enviaLogNomeFuncioalidadeAcessada(String funcionalidade) {
        enviaLog(LogDTO.of(funcionalidade));
    }
}