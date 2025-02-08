package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregadorRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.EntregaEventPublisher;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtribuirEntregadorUseCase {

    private final EntregaRepositoryImpl entregaRepository;
    private final EntregadorRepositoryImpl entregadorRepository;
    private final EntregaEventPublisher eventPublisher;

    public AtribuirEntregadorUseCase(EntregaRepositoryImpl entregaRepository,
                                     EntregadorRepositoryImpl entregadorRepository,
                                     EntregaEventPublisher eventPublisher) {
        this.entregaRepository = entregaRepository;
        this.entregadorRepository = entregadorRepository;
        this.eventPublisher = eventPublisher;
    }

    public EntregaDTO executar(UUID entregaId, UUID entregadorId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new ControllerNotFoundException("Entrega não encontrado."));

        entregadorRepository.findById(entregadorId)
                .orElseThrow(() -> new ControllerNotFoundException("Entregador não encontrado."));

        entrega.setEntregadorId(entregadorId);
        entrega.atualizarStatus(StatusEntrega.EM_TRANSITO);
        Entrega entregaAtualizada = entregaRepository.save(entrega);

        // Publicar evento no Kafka
        eventPublisher.publicarEntregaAtualizada(entregaAtualizada.getPedidoId(), entregaAtualizada.getStatus().toString());

        return EntregaMapper.toDTO(entregaAtualizada);
    }
}
