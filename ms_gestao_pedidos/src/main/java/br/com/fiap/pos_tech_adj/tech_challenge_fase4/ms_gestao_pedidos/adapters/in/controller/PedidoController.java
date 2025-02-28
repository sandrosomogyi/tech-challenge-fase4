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
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private final PedidoMapper pedidoMapper;

    @Autowired
    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            ConsultarPedidoUseCase consultarPedidoUseCase,
                            AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase,
                            PedidoMapper pedidoMapper) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.consultarPedidoUseCase = consultarPedidoUseCase;
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
        this.pedidoMapper = pedidoMapper;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        var pedidoCriado = criarPedidoUseCase.executar(pedidoDTO);
        return ResponseEntity.status(201).body(pedidoMapper.toDTO(pedidoCriado));
    }

    @PutMapping("/atualizar-status")
    public ResponseEntity<PedidoDTO> atualizarStatusPedido(@RequestParam("pedidoId") String pedidoId, @RequestParam("status") String status) {
        var pedidoAtualizado = atualizarStatusPedidoUseCase.executar(UUID.fromString(pedidoId), status);
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
        return ResponseEntity.ok(pedidos.stream().map(pedidoMapper::toDTO).collect(Collectors.toList()));
    }
}