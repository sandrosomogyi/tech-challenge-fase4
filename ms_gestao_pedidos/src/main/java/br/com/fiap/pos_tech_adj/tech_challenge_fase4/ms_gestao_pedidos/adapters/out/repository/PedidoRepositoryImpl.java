package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

public class PedidoRepositoryImpl implements PedidoRepository {

    private final SpringDataPedidoRepository repository;

    public PedidoRepositoryImpl(SpringDataPedidoRepository repository){ this.repository = repository; }

    @Override
    public Pedido save(Pedido cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pedido> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
