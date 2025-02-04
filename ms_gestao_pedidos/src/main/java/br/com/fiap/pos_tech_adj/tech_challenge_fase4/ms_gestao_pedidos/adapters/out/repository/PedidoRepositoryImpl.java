package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.PedidoRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoJpaRepository repository;

    public PedidoRepositoryImpl(PedidoJpaRepository repository){ this.repository = repository; }

    @Override
    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    @Override
    public Optional<Pedido> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Pedido> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
