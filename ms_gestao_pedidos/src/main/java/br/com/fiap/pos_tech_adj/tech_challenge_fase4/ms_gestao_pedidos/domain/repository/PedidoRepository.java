package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    Pedido save(Pedido produto);
    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    void delete(Long id);
}
