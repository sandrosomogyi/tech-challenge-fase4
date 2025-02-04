package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregaRepository {
    Optional<Entrega> findById(UUID id);
    List<Entrega> findByStatus(StatusEntrega status);
    Entrega save(Entrega entrega);
    void updateStatus(UUID id, StatusEntrega status);
}
