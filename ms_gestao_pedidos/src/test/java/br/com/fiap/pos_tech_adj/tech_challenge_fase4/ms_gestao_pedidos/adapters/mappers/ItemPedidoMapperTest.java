package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ItemPedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;


public class ItemPedidoMapperTest {

    @Test
    public void testToEntity() {
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(UUID.randomUUID());
        dto.setProdutoId(2L);
        dto.setQuantidade(3);
        dto.setTotal(new BigDecimal(100));

        ItemPedido entity = ItemPedidoMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getProdutoId(), entity.getProdutoId());
        assertNotNull(entity.getPedido());
        assertEquals(dto.getQuantidade(), entity.getQuantidade());
        assertEquals(dto.getTotal(), entity.getTotal());
    }

    @Test
    public void testToDTO() {
        ItemPedido entity = new ItemPedido();
        entity.setId(UUID.randomUUID());
        entity.setProdutoId(2L);
        entity.setPedido(new Pedido());
        entity.setQuantidade(3);
        entity.setTotal(new BigDecimal(100));

        ItemPedidoDTO dto = ItemPedidoMapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getProdutoId(), dto.getProdutoId());
        assertEquals(entity.getQuantidade(), dto.getQuantidade());
        assertEquals(entity.getTotal(), dto.getTotal());
    }
}