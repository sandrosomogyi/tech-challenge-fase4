package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.EntregaRepository;

import java.util.UUID;

public class CriarEntregaUseCase {

    private final EntregaRepository entregaRepository;

    public CriarEntregaUseCase(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public EntregaDTO executar(EntregaDTO entregaDTO) {
        Entrega entregaSalva = entregaRepository.save(EntregaMapper.toEntity(entregaDTO));
        return EntregaMapper.toDTO(entregaSalva);
    }
}
