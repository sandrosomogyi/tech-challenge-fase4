package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public CriarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido executar(Pedido pedido) {

        // Validar que o pedido possui itens
        if (pedido.getItens().isEmpty()) {
            throw new RuntimeException("O pedido deve conter pelo menos um item.");
        }

        // Calcular o valor total do pedido
        double total = pedido.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPrecoUnitario() * item.getQuantidade())
                .sum();
        pedido.setTotal(total);

        // Definir o status inicial do pedido
        pedido.setStatus(PedidoStatus.PENDENTE);

        // Salva o pedido no banco de dados
        return pedidoRepository.save(pedido);
    }
}
