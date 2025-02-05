package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;

public class EntregadorMapper {

    public static Entregador toEntity(EntregadorDTO dto) {
        return new Entregador(
                dto.getId(),
                dto.getNome(),
                dto.getTelefone(),
                dto.getVeiculo()
        );
    }

    public static EntregadorDTO toDTO(Entregador entity) {
        return new EntregadorDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getVeiculo()
        );
    }
}
