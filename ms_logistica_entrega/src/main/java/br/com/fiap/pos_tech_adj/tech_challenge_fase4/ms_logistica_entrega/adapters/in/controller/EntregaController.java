package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final CriarEntregaUseCase criarEntregaUseCase;
    private final FinalizarEntregaUseCase finalizarEntregaUseCase;
    private final CancelarEntregaUseCase cancelarEntregaUseCase;
    private final AtribuirEntregadorUseCase atribuirEntregadorUseCase;
    private final BuscarEntregaUseCase buscarEntregaUseCase;

    @Autowired
    public EntregaController(CriarEntregaUseCase criarEntregaUseCase,
                             FinalizarEntregaUseCase finalizarEntregaUseCase,
                             CancelarEntregaUseCase cancelarEntregaUseCase,
                             AtribuirEntregadorUseCase atribuirEntregadorUseCase,
                             BuscarEntregaUseCase buscarEntregaUseCase) {
        this.criarEntregaUseCase = criarEntregaUseCase;
        this.finalizarEntregaUseCase = finalizarEntregaUseCase;
        this.cancelarEntregaUseCase = cancelarEntregaUseCase;
        this.atribuirEntregadorUseCase = atribuirEntregadorUseCase;
        this.buscarEntregaUseCase = buscarEntregaUseCase;
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

    @PutMapping("/atribuir-entregador")
    public ResponseEntity<EntregaDTO> atribuirEntregador(@RequestParam("entregaId") UUID entregaId,
                                                         @RequestParam("entregadorId") UUID entregadorId) {
        EntregaDTO entrega = atribuirEntregadorUseCase.executar(entregaId, entregadorId);
        return ResponseEntity.ok(entrega);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> buscarEntregaPorId(@PathVariable UUID id) {
        Optional<EntregaDTO> entrega = buscarEntregaUseCase.buscarEntregaPorId(id);
        return entrega.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EntregaDTO>> listarEntregas() {
        List<EntregaDTO> entregas = buscarEntregaUseCase.buscarEntregas();
        return ResponseEntity.ok(entregas);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
