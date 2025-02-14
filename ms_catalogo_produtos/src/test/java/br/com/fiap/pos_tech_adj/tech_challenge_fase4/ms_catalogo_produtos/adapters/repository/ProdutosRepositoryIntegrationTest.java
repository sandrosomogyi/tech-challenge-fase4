package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.infra.database.repository.jpa.ProdutoJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProdutosRepositoryIntegrationTest {

    @Autowired
    private ProdutoJpaRepository produtoJpaRepository;

    @Test
    void testSalvarProduto() {
        Produto produto = new Produto();
        produto.setNome("Notebook Gamer");
        produto.setDescricao("Um ótimo notebook para jogos.");
        produto.setPreco(new BigDecimal("4999.99"));
        produto.setQuantidadeEstoque(5);

        Produto produtoSalvo = produtoJpaRepository.save(produto);

        assertNotNull(produtoSalvo.getId());
        assertEquals("Notebook Gamer", produtoSalvo.getNome());
    }


    @Test
    void testBuscarProdutoPorId() {
        Produto produto = new Produto();
        produto.setNome("Teclado Mecânico");
        produto.setDescricao("Teclado mecânico RGB");
        produto.setPreco(new BigDecimal("199.99"));
        produto.setQuantidadeEstoque(10);

        Produto produtoSalvo = produtoJpaRepository.save(produto);

        Optional<Produto> produtoBuscado = produtoJpaRepository.findById(produtoSalvo.getId());

        assertTrue(produtoBuscado.isPresent());
        assertEquals("Teclado Mecânico", produtoBuscado.get().getNome());
    }

    @Test
    void testListarProdutos() {
        Produto produto1 = new Produto();
        produto1.setNome("Monitor 24\"");
        produto1.setDescricao("Monitor Full HD");
        produto1.setPreco(new BigDecimal("899.99"));
        produto1.setQuantidadeEstoque(15);

        Produto produto2 = new Produto();
        produto2.setNome("Mouse Gamer");
        produto2.setDescricao("Mouse RGB com 7 botões");
        produto2.setPreco(new BigDecimal("149.99"));
        produto2.setQuantidadeEstoque(30);

        produtoJpaRepository.save(produto1);
        produtoJpaRepository.save(produto2);

        List<Produto> produtos = produtoJpaRepository.findAll();

        assertFalse(produtos.isEmpty());
        assertEquals(2, produtos.size());
    }

    @Test
    void testExcluirProduto() {
        Produto produto = new Produto();
        produto.setNome("Cadeira Gamer");
        produto.setDescricao("Cadeira confortável para longas horas de jogo");
        produto.setPreco(new BigDecimal("1299.99"));
        produto.setQuantidadeEstoque(7);

        Produto produtoSalvo = produtoJpaRepository.save(produto);
        Long id = produtoSalvo.getId();

        produtoJpaRepository.deleteById(id);

        Optional<Produto> produtoExcluido = produtoJpaRepository.findById(id);
        assertFalse(produtoExcluido.isPresent());
    }
}
