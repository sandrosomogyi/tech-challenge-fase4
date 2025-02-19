package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Pedido pedido;
    private UUID clienteId;

    @BeforeEach
    void setUp() {
        clienteId = UUID.randomUUID();
        pedido = new Pedido(clienteId, Collections.emptyList());
    }

    @Test
    void testCriacaoPedido() {
        assertNotNull(pedido.getId());
        assertEquals(clienteId, pedido.getClienteId());
        assertEquals(PedidoStatus.PENDENTE, pedido.getStatus());
        assertNotNull(pedido.getDataCriacao());
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void testConcluirPedido() {
        pedido.concluirPedido();
        assertEquals(PedidoStatus.CONCLUIDO, pedido.getStatus());
    }

    @Test
    void testCancelarPedido() {
        pedido.cancelarPedido();
        assertEquals(PedidoStatus.CANCELADO, pedido.getStatus());
    }

    @Test
    void testSetTotal() {
        BigDecimal valor = new BigDecimal("99.90");
        pedido.setTotal(valor);
        assertEquals(valor, pedido.getTotal());
    }

    @Test
    void testSetDataCriacao() {
        LocalDateTime novaData = LocalDateTime.now().minusDays(1);
        pedido.setDataCriacao(novaData);
        assertEquals(novaData, pedido.getDataCriacao());
    }
}


