package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarStatusPedidoUseCase {

    private final PedidoJpaRepository pedidoJpaRepository;

    public AtualizarStatusPedidoUseCase(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    public Pedido executar(UUID pedidoId, String status) {
        // Verifica se o pedido existe no banco de dados
        Pedido pedidoExistente = pedidoJpaRepository.findById(pedidoId)
                .orElseThrow(() -> new ControllerNotFoundException("Pedido não encontrado"));

        pedidoExistente.setStatus(PedidoStatus.valueOf(status));

        // Salva a atualização no banco de dados
        return pedidoJpaRepository.save(pedidoExistente);
    }
}
