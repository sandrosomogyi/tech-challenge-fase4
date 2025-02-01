package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoJpaRepository extends JpaRepository<ItemPedido, UUID> {
    // Método customizado para buscar itens de um pedido
    List<ItemPedido> findByPedidoId(UUID pedidoId);
    void deleteByPedidoId(UUID pedidoId);
}
