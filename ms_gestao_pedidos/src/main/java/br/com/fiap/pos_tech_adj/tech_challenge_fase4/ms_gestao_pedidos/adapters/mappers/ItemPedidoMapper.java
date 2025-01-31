package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ItemPedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;

public class ItemPedidoMapper {

    public static ItemPedido toEntity(ItemPedidoDTO dto) {
        return new ItemPedido(
                dto.getId(),
                ProdutoMapper.toEntity(dto.getProduto()),
                dto.getPedidoId(),
                dto.getQuantidade()
        );
    }

    public static ItemPedidoDTO toDTO(ItemPedido entity) {
        return new ItemPedidoDTO(
                entity.getId(),
                ProdutoMapper.toDTO(entity.getProduto()),
                entity.getPedidoId(),
                entity.getQuantidade()
        );
    }
}
