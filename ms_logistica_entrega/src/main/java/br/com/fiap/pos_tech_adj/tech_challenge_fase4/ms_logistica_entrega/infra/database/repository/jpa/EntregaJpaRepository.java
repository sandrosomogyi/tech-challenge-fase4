package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.infra.database.repository.jpa;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntregaJpaRepository extends JpaRepository<Entrega, UUID> {
    List<Entrega> findByStatus(StatusEntrega status);
    Optional<Entrega> findByCodigoRastreio(String codigoRastreio);
}
