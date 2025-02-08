package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.EntregaEventPublisher;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CancelarEntregaUseCase {

    private final EntregaRepositoryImpl entregaRepository;
    private final EntregaEventPublisher eventPublisher;

    public CancelarEntregaUseCase(EntregaRepositoryImpl entregaRepository,
                                  EntregaEventPublisher eventPublisher) {
        this.entregaRepository = entregaRepository;
        this.eventPublisher = eventPublisher;
    }

    public EntregaDTO executar(UUID pedidoId) {
        Entrega entrega = entregaRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ControllerNotFoundException("Entrega não encontrado."));

        entrega.atualizarStatus(StatusEntrega.CANCELADO);
        Entrega entregaAtualizada = entregaRepository.save(entrega);

        return EntregaMapper.toDTO(entregaAtualizada);
    }
}
