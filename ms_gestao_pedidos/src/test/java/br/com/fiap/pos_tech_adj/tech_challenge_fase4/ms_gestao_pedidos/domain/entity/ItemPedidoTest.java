package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ItemPedidoTest {
    
    private ItemPedido itemPedido;
    private UUID id;
    private Long produtoId;
    private Pedido pedido;
    private int quantidade;
    private BigDecimal total;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        produtoId = 1L;
        pedido = mock(Pedido.class); // Mock para evitar dependência real
        quantidade = 2;
        total = BigDecimal.valueOf(100.0);
        
        itemPedido = new ItemPedido(id, produtoId, pedido, quantidade, total);
    }

    @Test
    void testCriacaoItemPedido() {
        assertThat(itemPedido).isNotNull();
        assertThat(itemPedido.getId()).isEqualTo(id);
        assertThat(itemPedido.getProdutoId()).isEqualTo(produtoId);
        assertThat(itemPedido.getPedido()).isEqualTo(pedido);
        assertThat(itemPedido.getQuantidade()).isEqualTo(quantidade);
        assertThat(itemPedido.getTotal()).isEqualTo(total);
    }

    @Test
    void testSetters() {
        UUID novoId = UUID.randomUUID();
        Long novoProdutoId = 2L;
        Pedido novoPedido = mock(Pedido.class);
        int novaQuantidade = 5;
        BigDecimal novoTotal = BigDecimal.valueOf(250.0);

        itemPedido.setId(novoId);
        itemPedido.setProdutoId(novoProdutoId);
        itemPedido.setPedido(novoPedido);
        itemPedido.setQuantidade(novaQuantidade);
        itemPedido.setTotal(novoTotal);

        assertThat(itemPedido.getId()).isEqualTo(novoId);
        assertThat(itemPedido.getProdutoId()).isEqualTo(novoProdutoId);
        assertThat(itemPedido.getPedido()).isEqualTo(novoPedido);
        assertThat(itemPedido.getQuantidade()).isEqualTo(novaQuantidade);
        assertThat(itemPedido.getTotal()).isEqualTo(novoTotal);
    }
}