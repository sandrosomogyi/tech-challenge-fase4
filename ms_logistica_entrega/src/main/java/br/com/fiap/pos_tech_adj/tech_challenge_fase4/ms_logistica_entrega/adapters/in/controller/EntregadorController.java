package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.GerenciarEntregadorUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/entregadores")
public class EntregadorController {

    private final GerenciarEntregadorUseCase gerenciarEntregadorUseCase;

    @Autowired
    public EntregadorController(GerenciarEntregadorUseCase gerenciarEntregadorUseCase) {
        this.gerenciarEntregadorUseCase = gerenciarEntregadorUseCase;
    }

    @PostMapping
    public ResponseEntity<EntregadorDTO> cadastrarEntregador(@RequestBody EntregadorDTO entregadorDTO) {
        EntregadorDTO novoEntregador = gerenciarEntregadorUseCase.cadastrarEntregador(entregadorDTO);
        return ResponseEntity.ok(novoEntregador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregadorDTO> buscarEntregadorPorId(@PathVariable UUID id) {
        Optional<EntregadorDTO> entregador = gerenciarEntregadorUseCase.buscarEntregadorPorId(id);
        return entregador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregadorDTO> atualizarEntregador(@PathVariable UUID id, @RequestBody EntregadorDTO entregadorDTO) {
        Optional<EntregadorDTO> entregadorAtualizado = gerenciarEntregadorUseCase.atualizarEntregador(id, entregadorDTO);
        return entregadorAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEntregador(@PathVariable UUID id) {
        gerenciarEntregadorUseCase.removerEntregador(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EntregadorDTO>> listarEntregadores() {
        List<EntregadorDTO> entregadores = gerenciarEntregadorUseCase.buscarEntregadores();
        return ResponseEntity.ok(entregadores);
    }

        @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
