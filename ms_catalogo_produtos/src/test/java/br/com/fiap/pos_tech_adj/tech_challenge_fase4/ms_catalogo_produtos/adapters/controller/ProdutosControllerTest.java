package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.controller;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.in.controller.ProdutoController;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase.GerenciarEstoqueProdutoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase.GerenciarProdutoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutosControllerTest {
    @Mock
    private GerenciarProdutoUseCase gerenciarProdutoUseCase;

    @Mock
    private GerenciarEstoqueProdutoUseCase gerenciarEstoqueProdutoUseCase;

    @InjectMocks
    private ProdutoController produtoController;

    private ProdutoDTO produtoDTO;

    @BeforeEach
    void setUp() {
        produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNome("Produto Teste");

    }

    @Test
    void adicionarProduto_DeveRetornarProdutoCriado() {
        when(gerenciarProdutoUseCase.adicionarProduto(any(ProdutoDTO.class))).thenReturn(produtoDTO);

        ResponseEntity<ProdutoDTO> response = produtoController.adicionarProduto(produtoDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(produtoDTO, response.getBody());
    }

    @Test
    void atualizarProduto_DeveRetornarProdutoAtualizado() {
        when(gerenciarProdutoUseCase.atualizarProduto(eq(1L), any(ProdutoDTO.class))).thenReturn(produtoDTO);

        ResponseEntity<ProdutoDTO> response = produtoController.atualizarProduto(1L, produtoDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(produtoDTO, response.getBody());
    }

    @Test
    void listarProdutos_DeveRetornarListaDeProdutos() {
        when(gerenciarProdutoUseCase.listarProdutos()).thenReturn(Collections.singletonList(produtoDTO));

        ResponseEntity<List<ProdutoDTO>> response = produtoController.listarProdutos();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void buscarProdutoPorId_DeveRetornarProduto() {
        when(gerenciarProdutoUseCase.buscarProdutoPorId(1L)).thenReturn(produtoDTO);

        ResponseEntity<ProdutoDTO> response = produtoController.buscarProdutoPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(produtoDTO, response.getBody());
    }

    @Test
    void excluirProduto_DeveRetornarNoContent() {
        doNothing().when(gerenciarProdutoUseCase).excluirProduto(1L);

        ResponseEntity<Void> response = produtoController.excluirProduto(1L);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void acrescentarEstoqueProduto_DeveRetornarProdutoAtualizado() {
        when(gerenciarEstoqueProdutoUseCase.acrescentarEstoqueProduto(1L, 5)).thenReturn(produtoDTO);

        ResponseEntity<ProdutoDTO> response = produtoController.acrescentarEstoqueProduto(1L, 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(produtoDTO, response.getBody());
    }

    @Test
    void subtrairEstoqueProduto_DeveRetornarProdutoAtualizado() {
        when(gerenciarEstoqueProdutoUseCase.subtrairEstoqueProduto(1L, 3)).thenReturn(produtoDTO);

        ResponseEntity<ProdutoDTO> response = produtoController.subtrairEstoqueProduto(1L, 3);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(produtoDTO, response.getBody());
    }
}
