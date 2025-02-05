package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.EntregadorRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.infra.database.repository.jpa.EntregadorJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EntregadorRepositoryImpl implements EntregadorRepository {

    private final EntregadorJpaRepository repository;

    public EntregadorRepositoryImpl(EntregadorJpaRepository repository){ this.repository = repository; }

    @Override
    public Optional<Entregador> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Entregador> findAll() {
        return repository.findAll();
    }

    @Override
    public Entregador save(Entregador entregador) {
        return repository.save(entregador);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
