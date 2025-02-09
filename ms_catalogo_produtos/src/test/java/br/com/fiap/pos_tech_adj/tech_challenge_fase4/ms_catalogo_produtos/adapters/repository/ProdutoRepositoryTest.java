package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.out.repository.ProdutoRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.infra.database.repository.jpa.ProdutoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoRepositoryTest {

    @Mock
    private ProdutoJpaRepository produtoJpaRepository;

    @InjectMocks
    private ProdutoRepositoryImpl produtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setPreco(new BigDecimal("99.99"));
        produto.setQuantidadeEstoque(10);

        when(produtoJpaRepository.save(produto)).thenReturn(produto);

        Produto savedProduto = produtoRepository.save(produto);

        assertNotNull(savedProduto);
        assertEquals(produto.getId(), savedProduto.getId());
        verify(produtoJpaRepository, times(1)).save(produto);
    }

    @Test
    void testFindById() {
        Produto produto = new Produto();
        produto.setId(1L);

        when(produtoJpaRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> foundProduto = produtoRepository.findById(1L);

        assertTrue(foundProduto.isPresent());
        assertEquals(1L, foundProduto.get().getId());
        verify(produtoJpaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAll() {
        Produto produto1 = new Produto();
        produto1.setId(1L);

        Produto produto2 = new Produto();
        produto2.setId(2L);

        List<Produto> produtos = Arrays.asList(produto1, produto2);

        when(produtoJpaRepository.findAll()).thenReturn(produtos);

        List<Produto> foundProdutos = produtoRepository.findAll();

        assertNotNull(foundProdutos);
        assertEquals(2, foundProdutos.size());
        verify(produtoJpaRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(produtoJpaRepository).deleteById(id);

        produtoRepository.delete(id);

        verify(produtoJpaRepository, times(1)).deleteById(id);
    }

}
