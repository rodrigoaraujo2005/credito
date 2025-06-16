package br.com.gestionna.credito.rest;

import br.com.gestionna.credito.entity.Credito;
import br.com.gestionna.credito.service.CreditoService;
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
    
    private final CreditoService creditoService;
    
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<Credito>> getCreditosByNumeroNfse(@PathVariable String numeroNfse) {
        List<Credito> creditos = creditoService.findByNumeroNfse(numeroNfse);
        if (creditos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditos);
    }
    
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<Credito> getCreditoByNumeroCredito(@PathVariable String numeroCredito) {
        Optional<Credito> credito = creditoService.findByNumeroCredito(numeroCredito);
        return credito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Credito>> getAllCreditos() {
        List<Credito> creditos = creditoService.findAll();
        return ResponseEntity.ok(creditos);
    }
}

