package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPedidoRepository extends JpaRepository<Pedido, Long> {
}
