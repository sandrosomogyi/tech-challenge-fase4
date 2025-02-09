package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mappers.ProdutoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GerenciarEstoqueProdutoUseCaseTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private GerenciarEstoqueProdutoUseCase gerenciarEstoqueProdutoUseCase;

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setId(1L);
        produto.setQuantidadeEstoque(10);
    }

    @Test
    void testAcrescentarEstoqueProduto() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        when(produtoMapper.toDTO(any(Produto.class))).thenReturn(new ProdutoDTO());

        ProdutoDTO resultado = gerenciarEstoqueProdutoUseCase.acrescentarEstoqueProduto(1L, 5);
        assertNotNull(resultado);
        assertEquals(15, produto.getQuantidadeEstoque());
    }

    @Test
    void testSubtrairEstoqueProduto_Sucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        when(produtoMapper.toDTO(any(Produto.class))).thenReturn(new ProdutoDTO());

        ProdutoDTO resultado = gerenciarEstoqueProdutoUseCase.subtrairEstoqueProduto(1L, 5);
        assertNotNull(resultado);
        assertEquals(5, produto.getQuantidadeEstoque());
    }

    @Test
    void testSubtrairEstoqueProduto_EstoqueInsuficiente() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        assertThrows(ControllerMessagingException.class, () ->
                gerenciarEstoqueProdutoUseCase.subtrairEstoqueProduto(1L, 15));
    }

    @Test
    void testSubtrairEstoqueProduto_ProdutoNaoEncontrado() {
        when(produtoRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () ->
                gerenciarEstoqueProdutoUseCase.subtrairEstoqueProduto(2L, 5));
    }
}