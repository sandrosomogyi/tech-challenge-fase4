package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AtualizarPedidoUseCase {

    private final PedidoJpaRepository pedidoJpaRepository;

    public AtualizarPedidoUseCase(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    public Pedido executar(UUID pedidoId, Pedido pedidoAtualizado) {
        // Verifica se o pedido existe no banco de dados
        Pedido pedidoExistente = pedidoJpaRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualiza os dados do pedido
        pedidoExistente.setItens(pedidoAtualizado.getItens());
        pedidoExistente.setStatus(pedidoAtualizado.getStatus());

        // Recalcula o total do pedido, caso tenha mudado a lista de itens
        BigDecimal total = pedidoExistente.getItens().stream()
                .map(item -> item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedidoExistente.setTotal(total);

        // Salva a atualização no banco de dados
        return pedidoJpaRepository.save(pedidoExistente);
    }
}
