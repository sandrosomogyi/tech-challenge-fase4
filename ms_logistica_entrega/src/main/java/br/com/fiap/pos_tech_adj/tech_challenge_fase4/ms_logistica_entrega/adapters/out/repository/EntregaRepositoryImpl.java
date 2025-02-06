package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.EntregaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.infra.database.repository.jpa.EntregaJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EntregaRepositoryImpl implements EntregaRepository {

    private final EntregaJpaRepository repository;

    public EntregaRepositoryImpl(EntregaJpaRepository repository){ this.repository = repository; }

    @Override
    public Optional<Entrega> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Entrega> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Entrega> findByStatus(StatusEntrega status) {
        return repository.findByStatus(status);
    }

    @Override
    public Optional<Entrega> findByCodigoRastreio(String id) {
        return repository.findByCodigoRastreio(id);
    }

    @Override
    public Entrega save(Entrega entrega) {
        return repository.save(entrega);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
