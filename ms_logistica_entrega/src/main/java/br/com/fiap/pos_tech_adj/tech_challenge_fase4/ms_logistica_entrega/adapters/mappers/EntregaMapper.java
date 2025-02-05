package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;

public class EntregaMapper {

    public static Entrega toEntity(EntregaDTO dto) {
        return new Entrega(
                dto.getPedidoId(),
                dto.getEntregadorId(),
                dto.getEnderecoDestino(),
                dto.getDataHoraPrevista()
        );
    }

    public static EntregaDTO toDTO(Entrega entity) {
        return new EntregaDTO(
                entity.getId(),
                entity.getPedidoId(),
                entity.getEntregadorId(),
                entity.getEnderecoDestino(),
                entity.getStatus(),
                entity.getDataHoraPrevista(),
                entity.getDataHoraConclusao(),
                entity.getCodigoRastreamento()
        );
    }
}
