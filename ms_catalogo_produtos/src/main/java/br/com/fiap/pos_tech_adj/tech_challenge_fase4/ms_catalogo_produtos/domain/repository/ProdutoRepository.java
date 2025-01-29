package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Produto save(Produto produto);
    List<Produto> findAll();
    Optional<Produto> findById(Long id);
    void delete(Long id);

}
