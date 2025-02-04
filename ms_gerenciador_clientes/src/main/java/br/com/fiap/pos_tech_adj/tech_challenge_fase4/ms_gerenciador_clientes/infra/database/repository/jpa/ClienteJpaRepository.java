package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.infra.database.repository.jpa;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteJpaRepository extends JpaRepository<Cliente, UUID> {
}