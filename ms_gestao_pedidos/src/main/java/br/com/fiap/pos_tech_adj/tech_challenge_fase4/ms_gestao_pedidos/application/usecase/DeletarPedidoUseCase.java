package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository.ItemPedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletarPedidoUseCase {

    @Autowired
    private PedidoJpaRepository pedidoJpaRepository;

    @Autowired
    private ItemPedidoJpaRepository itemPedidoJpaRepository;

    public void deletarPedido(UUID pedidoId) {
        // Verificar se o pedido existe
        var pedido = pedidoJpaRepository.findById(pedidoId)
                .orElseThrow(() -> new ControllerNotFoundException("Pedido não encontrado"));

        // Deletar todos os itens relacionados ao pedido
        itemPedidoJpaRepository.deleteByPedidoId(pedidoId);

        // Deletar o pedido
        pedidoJpaRepository.delete(pedido);
    }
}
