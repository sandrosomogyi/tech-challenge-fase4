package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.repository.ProdutoRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mappers.ProdutoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenciarProdutoUseCaseTest {

    @InjectMocks
    private GerenciarProdutoUseCase gerenciarProdutoUseCase;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    private ProdutoDTO produtoDTO;
    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Preparando objetos ProdutoDTO e Produto corretamente para os testes
        produtoDTO = new ProdutoDTO(1L, "Produto 1", "Descrição do Produto", new BigDecimal("100.0"), 10);
        produto = new Produto(1L, "Produto 1", "Descrição do Produto", new BigDecimal("100.0"), 10);
    }

    @Test
    void testAtualizarProduto() {
        when(produtoRepository.findById(produtoDTO.getId())).thenReturn(Optional.of(produto));
        when(produtoRepository.save(produto)).thenReturn(produto);
        when(produtoMapper.toDTO(produto)).thenReturn(produtoDTO);

        ProdutoDTO resultado = gerenciarProdutoUseCase.atualizarProduto(produtoDTO.getId(), produtoDTO);

        assertNotNull(resultado);
        assertEquals(produtoDTO.getNome(), resultado.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void testBuscarProdutoPorId() {
        when(produtoRepository.findById(produtoDTO.getId())).thenReturn(Optional.of(produto));
        when(produtoMapper.toDTO(produto)).thenReturn(produtoDTO);

        ProdutoDTO resultado = gerenciarProdutoUseCase.buscarProdutoPorId(produtoDTO.getId());

        assertNotNull(resultado);
        assertEquals(produtoDTO.getNome(), resultado.getNome());
        verify(produtoRepository, times(1)).findById(produtoDTO.getId());
    }

    @Test
    void testBuscarProdutoPorIdProdutoNaoEncontrado() {
        when(produtoRepository.findById(produtoDTO.getId())).thenReturn(Optional.empty());

        ControllerNotFoundException exception = assertThrows(ControllerNotFoundException.class, () -> {
            gerenciarProdutoUseCase.buscarProdutoPorId(produtoDTO.getId());
        });

        assertEquals("Produto não encontrado", exception.getMessage());
    }

    @Test
    void testExcluirProduto() {
        when(produtoRepository.findById(produtoDTO.getId())).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).delete(produto.getId());

        gerenciarProdutoUseCase.excluirProduto(produtoDTO.getId());

        verify(produtoRepository, times(1)).delete(produto.getId());
    }

    @Test
    void testExcluirProdutoNaoEncontrado() {
        when(produtoRepository.findById(produtoDTO.getId())).thenReturn(Optional.empty());

        ControllerNotFoundException exception = assertThrows(ControllerNotFoundException.class, () -> {
            gerenciarProdutoUseCase.excluirProduto(produtoDTO.getId());
        });

        assertEquals("Produto não encontrado", exception.getMessage());
    }


}
