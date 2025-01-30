package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mappers.ProdutoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenciarEstoqueProdutoUseCase {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Autowired
    public GerenciarEstoqueProdutoUseCase(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    // Adicionar novos produtos ao estoque
    public ProdutoDTO acrescentarEstoqueProduto(Long id, Integer acrescentarEstoque) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));

        produtoExistente.setQuantidadeEstoque(produtoExistente.getQuantidadeEstoque() + acrescentarEstoque);

        produtoRepository.save(produtoExistente);  // Atualiza no banco de dados
        return produtoMapper.toDTO(produtoExistente);  // Retorna o DTO atualizado
    }

    // Subtrair produto do estoque
    public ProdutoDTO subtrairEstoqueProduto(Long id, Integer subtrairEstoque) {
        // Valida a quantidade a ser subtraída
        if (subtrairEstoque <= 0) {
            throw new ControllerMessagingException("A quantidade a ser subtraída deve ser maior que zero.");
        }

        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));

        var estoqueFinal = produtoExistente.getQuantidadeEstoque() - subtrairEstoque;

        if (estoqueFinal < 0) {
            throw new ControllerMessagingException("Estoque insuficiente.");
        }

        produtoExistente.setQuantidadeEstoque(estoqueFinal);

        produtoRepository.save(produtoExistente);  // Atualiza no banco de dados
        return produtoMapper.toDTO(produtoExistente);  // Retorna o DTO atualizado
    }
}
