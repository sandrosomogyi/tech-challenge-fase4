package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.RotaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.infra.database.repository.jpa.RotaJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RotaRepositoryImpl implements RotaRepository {

    private final RotaJpaRepository repository;

    public RotaRepositoryImpl(RotaJpaRepository repository){ this.repository = repository; }

    @Override
    public Optional<Rota> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Rota> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Rota> findByEntregaId(UUID entregaId) {
        return repository.findByEntregaId(entregaId);
    }

    @Override
    public Rota save(Rota rota) {
        return repository.save(rota);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
