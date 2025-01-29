package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.repository.ProdutoRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mappers.ProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenciarProdutoUseCase {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Autowired
    public GerenciarProdutoUseCase(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    // Adicionar um novo produto
    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produto = produtoRepository.save(produto);  // Salva no banco de dados
        return produtoMapper.toDTO(produto);  // Retorna o DTO do produto salvo
    }

    // Atualizar um produto existente
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));

        produtoExistente.setNome(produtoDTO.getNome());
        produtoExistente.setDescricao(produtoDTO.getDescricao());
        produtoExistente.setPreco(produtoDTO.getPreco());
        produtoExistente.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());

        produtoRepository.save(produtoExistente);  // Atualiza no banco de dados
        return produtoMapper.toDTO(produtoExistente);  // Retorna o DTO atualizado
    }

    // Listar todos os produtos
    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();  // Busca todos os produtos no banco de dados
        return produtoMapper.toDTOList(produtos);  // Converte para lista de DTOs
    }

    // Buscar produto por ID
    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return produtoMapper.toDTO(produto);  // Retorna o DTO do produto encontrado
    }

    // Excluir um produto
    public void excluirProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        produtoRepository.delete(produto.getId());  // Exclui o produto do banco de dados
    }
}
