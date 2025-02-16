package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import org.springframework.stereotype.Component;

@Component
public class RotaMapper {

    public static Rota toEntity(RotaDTO dto) {
        return new Rota(
                dto.getId(),
                dto.getEntregaId(),
                dto.getPontoPartida(),
                dto.getDestino(),
                dto.getDistancia(),
                dto.getTempoEstimado(),
                dto.getDataSaida()
        );
    }

    public static RotaDTO toDTO(Rota entity) {
        return new RotaDTO(
                entity.getId(),
                entity.getEntregaId(),
                entity.getPontoPartida(),
                entity.getDestino(),
                entity.getDistancia(),
                entity.getTempoEstimado(),
                entity.getDataSaida()
        );
    }
}