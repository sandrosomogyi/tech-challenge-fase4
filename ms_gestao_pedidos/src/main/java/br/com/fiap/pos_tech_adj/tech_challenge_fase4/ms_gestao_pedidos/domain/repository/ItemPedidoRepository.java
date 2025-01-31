package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemPedidoRepository {
    ItemPedido save(ItemPedido itemPedido);
    List<ItemPedido> findAll();
    Optional<ItemPedido> findById(UUID id);
    List<ItemPedido> findByPedidoId(UUID id);
    void delete(UUID id);
    void deleteByPedidoId(UUID pedidoID);
}
