package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository.ItemPedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ConsultarItensPedidoUseCase {

    private final ItemPedidoJpaRepository itemPedidoJpaRepository;

    public ConsultarItensPedidoUseCase(ItemPedidoJpaRepository itemPedidoJpaRepository) {
        this.itemPedidoJpaRepository = itemPedidoJpaRepository;
    }

    public List<ItemPedido> executar(UUID pedidoId) {
        // Recupera todos os itens de um pedido
        return itemPedidoJpaRepository.findByPedidoId(pedidoId);
    }
}
