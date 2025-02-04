package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.ItemPedidoRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.ItemPedidoJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ItemPedidoRepositoryImpl implements ItemPedidoRepository {

    private final ItemPedidoJpaRepository repository;

    public ItemPedidoRepositoryImpl(ItemPedidoJpaRepository repository){ this.repository = repository; }

    @Override
    public ItemPedido save(ItemPedido itemPedido) {
        return repository.save(itemPedido);
    }

    @Override
    public Optional<ItemPedido> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<ItemPedido> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
