package br.com.gestionna.credito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private String funcionalidade;

    private LocalDateTime dataHoraAcesso;

    public static LogDTO of(String funcionalidade) {
        return new LogDTO(funcionalidade, LocalDateTime.now());
    }
}