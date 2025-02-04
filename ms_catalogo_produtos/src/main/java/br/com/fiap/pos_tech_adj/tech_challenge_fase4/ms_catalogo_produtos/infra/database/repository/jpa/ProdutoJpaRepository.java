package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.infra.database.repository.jpa;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoJpaRepository extends JpaRepository<Produto, Long> {
}