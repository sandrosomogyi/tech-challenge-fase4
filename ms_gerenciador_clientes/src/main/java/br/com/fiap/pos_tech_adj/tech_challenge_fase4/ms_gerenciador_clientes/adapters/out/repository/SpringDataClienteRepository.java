package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataClienteRepository extends JpaRepository<Cliente, UUID> {
}