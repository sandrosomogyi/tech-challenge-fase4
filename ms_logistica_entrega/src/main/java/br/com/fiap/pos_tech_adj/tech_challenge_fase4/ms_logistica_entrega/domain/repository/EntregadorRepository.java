package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregadorRepository {
    Optional<Entregador> findById(UUID id);
    List<Entregador> findAll();
    Entregador save(Entregador entregador);
    void deleteById(UUID id);
}
