package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProdutoRepository extends JpaRepository<Produto, Long> {
}