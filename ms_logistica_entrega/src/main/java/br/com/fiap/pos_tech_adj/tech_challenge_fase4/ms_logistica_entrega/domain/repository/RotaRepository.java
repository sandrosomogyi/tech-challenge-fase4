package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RotaRepository {
    Optional<Rota> findById(UUID id);
    List<Rota> findAll();
    List<Rota> findByEntregaId(UUID entregaId);
    Rota save(Rota rota);
    void deleteById(UUID id);
}
