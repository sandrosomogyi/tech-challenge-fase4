package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mapper;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mappers.ProdutoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {

    private ProdutoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        produtoMapper = new ProdutoMapper();
    }

    @Test
    void testToDTO() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do Produto");
        produto.setPreco(new BigDecimal("99.99"));
        produto.setQuantidadeEstoque(10);

        ProdutoDTO dto = produtoMapper.toDTO(produto);

        assertNotNull(dto);
        assertEquals(produto.getId(), dto.getId());
        assertEquals(produto.getNome(), dto.getNome());
        assertEquals(produto.getDescricao(), dto.getDescricao());
        assertEquals(produto.getPreco(), dto.getPreco());
        assertEquals(produto.getQuantidadeEstoque(), dto.getQuantidadeEstoque());
    }

    @Test
    void testToEntity() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(1L);
        dto.setNome("Produto Teste");
        dto.setDescricao("Descrição do Produto");
        dto.setPreco(new BigDecimal("99.99"));
        dto.setQuantidadeEstoque(10);

        Produto produto = produtoMapper.toEntity(dto);

        assertNotNull(produto);
        assertEquals(dto.getId(), produto.getId());
        assertEquals(dto.getNome(), produto.getNome());
        assertEquals(dto.getDescricao(), produto.getDescricao());
        assertEquals(dto.getPreco(), produto.getPreco());
        assertEquals(dto.getQuantidadeEstoque(), produto.getQuantidadeEstoque());
    }

    @Test
    void testToDTOList() {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto 1");
        produto1.setDescricao("Descrição 1");
        produto1.setPreco(new BigDecimal("50.00"));
        produto1.setQuantidadeEstoque(5);

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto 2");
        produto2.setDescricao("Descrição 2");
        produto2.setPreco(new BigDecimal("75.00"));
        produto2.setQuantidadeEstoque(8);

        List<Produto> produtos = List.of(produto1, produto2);
        List<ProdutoDTO> dtos = produtoMapper.toDTOList(produtos);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());

        assertEquals(produto1.getId(), dtos.get(0).getId());
        assertEquals(produto1.getNome(), dtos.get(0).getNome());

        assertEquals(produto2.getId(), dtos.get(1).getId());
        assertEquals(produto2.getNome(), dtos.get(1).getNome());
    }
}
