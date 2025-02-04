package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.PedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.*;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final ConsultarPedidoUseCase consultarPedidoUseCase;
    private final DeletarPedidoUseCase deletarPedidoUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private final PedidoMapper pedidoMapper;

    @Autowired
    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            ConsultarPedidoUseCase consultarPedidoUseCase,
                            DeletarPedidoUseCase deletarPedidoUseCase,
                            AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase,
                            PedidoMapper pedidoMapper) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.consultarPedidoUseCase = consultarPedidoUseCase;
        this.deletarPedidoUseCase = deletarPedidoUseCase;
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
        this.pedidoMapper = pedidoMapper;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        var pedidoCriado = criarPedidoUseCase.executar(pedidoDTO);
        return ResponseEntity.status(201).body(pedidoMapper.toDTO(pedidoCriado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarStatusPedido(@PathVariable UUID id, @RequestParam("status") String status) {
        var pedidoAtualizado = atualizarStatusPedidoUseCase.executar(id, status);
        return ResponseEntity.ok(pedidoMapper.toDTO(pedidoAtualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> consultarPedido(@PathVariable UUID id) {
        var pedido = consultarPedidoUseCase.executar(id);
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        var pedidos = consultarPedidoUseCase.executarTodos();
        return ResponseEntity.ok(pedidos.stream().map(PedidoMapper::toDTO).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        deletarPedidoUseCase.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}