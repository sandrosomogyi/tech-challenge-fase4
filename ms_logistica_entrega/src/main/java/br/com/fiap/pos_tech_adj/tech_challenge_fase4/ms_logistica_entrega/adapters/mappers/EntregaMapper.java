package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import org.springframework.stereotype.Component;

@Component
public class EntregaMapper {

    public static Entrega toEntity(EntregaDTO dto) {
        return new Entrega(
                dto.getId(),
                dto.getPedidoId(),
                dto.getEntregadorId(),
                dto.getEnderecoDestino(),
                dto.getStatus(),
                dto.getDataHoraPrevista(),
                dto.getDataHoraConclusao(),
                dto.getCodigoRastreamento()
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
