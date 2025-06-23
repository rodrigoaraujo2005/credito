package br.com.gestionna.credito.service;

import br.com.gestionna.credito.dto.LogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;


@Service
@Slf4j
public class LogService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @KafkaListener(topics = "${app.kafka.topic.api-access-log}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeApiAccessLog(LogDTO logs) {
        String formattedTime = "null";
        if (logs.getDataHoraAcesso() != null) {
            formattedTime = logs.getDataHoraAcesso().format(DATE_TIME_FORMATTER);
        }

        log.info("Log: Funcionalidade '{}' acessada em: {}",
                logs.getFuncionalidade(),
                formattedTime);
    }
}
