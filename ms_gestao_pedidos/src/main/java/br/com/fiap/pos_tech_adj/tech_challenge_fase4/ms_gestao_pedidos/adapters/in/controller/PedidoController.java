package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.PedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.CriarPedidoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.ConsultarPedidoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers.PedidoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.DeletarPedidoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final ConsultarPedidoUseCase consultarPedidoUseCase;
    private final DeletarPedidoUseCase deletarPedidoUseCase;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            ConsultarPedidoUseCase consultarPedidoUseCase,
                            DeletarPedidoUseCase deletarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.consultarPedidoUseCase = consultarPedidoUseCase;
        this.deletarPedidoUseCase = deletarPedidoUseCase;
    }

    // Endpoint para criar pedido
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        return PedidoMapper.toDTO(criarPedidoUseCase.executar(PedidoMapper.toEntity(pedidoDTO)));
    }

    // Endpoint para consultar pedidos por ID
    @GetMapping("/{id}")
    public PedidoDTO consultarPedido(@PathVariable UUID id) {
        return PedidoMapper.toDTO(consultarPedidoUseCase.executar(id));
    }

    // Endpoint para listar todos os pedidos
    @GetMapping
    public List<PedidoDTO> listarPedidos() {
        return consultarPedidoUseCase.executarTodos().stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID pedidoId) {
        try {
            deletarPedidoUseCase.deletarPedido(pedidoId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
