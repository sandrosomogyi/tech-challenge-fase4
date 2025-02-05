package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.AtribuirEntregadorUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CancelarEntregaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CriarEntregaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.FinalizarEntregaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final CriarEntregaUseCase criarEntregaUseCase;
    private final FinalizarEntregaUseCase finalizarEntregaUseCase;
    private final CancelarEntregaUseCase cancelarEntregaUseCase;
    private final AtribuirEntregadorUseCase atribuirEntregadorUseCase;

    @Autowired
    public EntregaController(CriarEntregaUseCase criarEntregaUseCase,
                             FinalizarEntregaUseCase finalizarEntregaUseCase,
                             CancelarEntregaUseCase cancelarEntregaUseCase,
                             AtribuirEntregadorUseCase atribuirEntregadorUseCase) {
        this.criarEntregaUseCase = criarEntregaUseCase;
        this.finalizarEntregaUseCase = finalizarEntregaUseCase;
        this.cancelarEntregaUseCase = cancelarEntregaUseCase;
        this.atribuirEntregadorUseCase = atribuirEntregadorUseCase;
    }

    @PostMapping
    public ResponseEntity<EntregaDTO> criarEntrega(@RequestBody EntregaDTO entregaDTO) {
        EntregaDTO novaEntrega = criarEntregaUseCase.executar(entregaDTO);
        return ResponseEntity.status(201).body(novaEntrega);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<EntregaDTO> finalizarEntrega(@PathVariable UUID id) {
        EntregaDTO entrega =  finalizarEntregaUseCase.executar(id);
        return ResponseEntity.ok(entrega);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<EntregaDTO> cancelarEntrega(@PathVariable UUID id) {
        EntregaDTO entrega = cancelarEntregaUseCase.executar(id);
        return ResponseEntity.ok(entrega);
    }

    @PutMapping("/atribuir-entregador/{id}")
    public ResponseEntity<EntregaDTO> atribuirEntregador(@PathVariable UUID entregaId,
                                                         @RequestParam("entregadorId") UUID entregadorId) {
        EntregaDTO entrega = atribuirEntregadorUseCase.executar(entregaId, entregadorId);
        return ResponseEntity.ok(entrega);
    }
}
