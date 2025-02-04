package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ItemPedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;

public class ItemPedidoMapper {

    public static ItemPedido toEntity(ItemPedidoDTO dto) {
        ItemPedido item = new ItemPedido();
        item.setId(dto.getId());
        item.setProdutoId(dto.getProdutoId());
        item.setPedido(new Pedido());
        item.setQuantidade(dto.getQuantidade());
        item.setTotal(dto.getTotal());

        return item;
    }

    public static ItemPedidoDTO toDTO(ItemPedido entity) {
        return new ItemPedidoDTO(
                entity.getId(),
                entity.getProdutoId(),
                entity.getQuantidade(),
                entity.getTotal()
        );
    }
}
