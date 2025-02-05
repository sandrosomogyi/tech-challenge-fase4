package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.EntregaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.EntregadorRepository;

import java.util.Optional;
import java.util.UUID;

public class AtribuirEntregadorUseCase {

    private final EntregaRepository entregaRepository;
    private final EntregadorRepository entregadorRepository;

    public AtribuirEntregadorUseCase(EntregaRepository entregaRepository, EntregadorRepository entregadorRepository) {
        this.entregaRepository = entregaRepository;
        this.entregadorRepository = entregadorRepository;
    }

    public EntregaDTO executar(UUID entregaId, UUID entregadorId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new ControllerNotFoundException("Entrega não encontrado."));

        entregadorRepository.findById(entregadorId)
                .orElseThrow(() -> new ControllerNotFoundException("Entregador não encontrado."));

        entrega.setEntregadorId(entregadorId);
        entrega.atualizarStatus(StatusEntrega.EM_TRANSITO);
        Entrega entregaAtualizada = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(entregaAtualizada);
    }
}
