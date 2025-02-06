package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CalcularRotaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.RastrearEntregaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/rotas")
public class RotaController {

    private final CalcularRotaUseCase calcularRotaUseCase;
    private final RastrearEntregaUseCase rastrearEntregaUseCase;

    @Autowired
    public RotaController(CalcularRotaUseCase calcularRotaUseCase, RastrearEntregaUseCase rastrearEntregaUseCase) {
        this.calcularRotaUseCase = calcularRotaUseCase;
        this.rastrearEntregaUseCase = rastrearEntregaUseCase;
    }

    @PostMapping("/calcular")
    public ResponseEntity<RotaDTO> calcularRota(@RequestBody Map<String, String> params) {
        UUID entregaId = UUID.fromString(params.get("entregaId"));
        String origem = params.get("origem");
        String destino = params.get("destino");

        RotaDTO rotaDTO = calcularRotaUseCase.calcularMelhorRota(entregaId, origem, destino);
        return ResponseEntity.ok(rotaDTO);
    }


    @GetMapping("/rastrear/{entregaId}")
    public ResponseEntity<List<RotaDTO>> rastrearEntrega(@PathVariable UUID entregaId) {
        List<RotaDTO> rotas = rastrearEntregaUseCase.rastrearRotasPorEntregaId(entregaId);
        return ResponseEntity.ok(rotas);
    }


}
