package br.com.gestionna.credito.rest;

import br.com.gestionna.credito.dto.CreditoDTO;
import br.com.gestionna.credito.entity.Credito;
import br.com.gestionna.credito.mapper.CreditoMapper;
import br.com.gestionna.credito.service.ICreditoService;
import br.com.gestionna.credito.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/creditos")
@CrossOrigin(origins = "*")
public class CreditoController {

    private final ICreditoService creditoService;
    private final KafkaProducerService kafkaProducerService;
    private final CreditoMapper creditoMapper;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> getCreditosByNumeroNfse(@PathVariable String numeroNfse) {
        // Log API access to Kafka
        kafkaProducerService.enviaLogNomeFuncioalidadeAcessada("getCreditosByNumeroNfse");

        List<Credito> creditos = creditoService.findByNumeroNfse(numeroNfse);
        if (creditos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditoMapper.toDTOList(creditos));
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> getCreditoByNumeroCredito(@PathVariable String numeroCredito) {
        kafkaProducerService.enviaLogNomeFuncioalidadeAcessada("getCreditoByNumeroCredito");
        Optional<Credito> credito = creditoService.findByNumeroCredito(numeroCredito);
        return credito.map(entity -> ResponseEntity.ok(creditoMapper.toDTO(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CreditoDTO>> getAllCreditos() {
        kafkaProducerService.enviaLogNomeFuncioalidadeAcessada("getAllCreditos");

        List<Credito> creditos = creditoService.findAll();
        return ResponseEntity.ok(creditoMapper.toDTOList(creditos));
    }
}
