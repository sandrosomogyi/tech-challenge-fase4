package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.repository.ProdutoRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.infra.database.repository.jpa.ProdutoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoJpaRepository repository;

    public ProdutoRepositoryImpl(ProdutoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto save(Produto cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void saveAll(List<? extends Produto> produtos){ repository.saveAll(produtos); }
}
