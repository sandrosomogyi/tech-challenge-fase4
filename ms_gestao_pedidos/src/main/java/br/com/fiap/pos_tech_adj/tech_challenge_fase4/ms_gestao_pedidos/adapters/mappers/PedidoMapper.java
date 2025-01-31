package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.PedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static Pedido toEntity(PedidoDTO dto) {
        return new Pedido(
                dto.getId(),
                ClienteMapper.toEntity(dto.getCliente()),
                dto.getItens().stream()
                        .map(ItemPedidoMapper::toEntity)
                        .collect(Collectors.toList()),
                dto.getDataCriacao(),
                dto.getTotal(),
                PedidoStatus.valueOf(dto.getStatus()) // Converte String para Enum
        );
    }

    public static PedidoDTO toDTO(Pedido entity) {
        return new PedidoDTO(
                entity.getId(),
                ClienteMapper.toDTO(entity.getCliente()),
                entity.getItens().stream()
                        .map(ItemPedidoMapper::toDTO)
                        .collect(Collectors.toList()),
                entity.getDataCriacao(),
                entity.getTotal(),
                entity.getStatus().name() // Converte Enum para String
        );
    }
}
