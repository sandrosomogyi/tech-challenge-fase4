package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.ItemPedidoRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletarPedidoUseCase {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public void deletarPedido(UUID pedidoId) {
        // Verificar se o pedido existe
        var pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Deletar todos os itens relacionados ao pedido
        itemPedidoRepository.deleteByPedidoId(pedidoId);

        // Deletar o pedido
        pedidoRepository.delete(pedido.getId());
    }
}
