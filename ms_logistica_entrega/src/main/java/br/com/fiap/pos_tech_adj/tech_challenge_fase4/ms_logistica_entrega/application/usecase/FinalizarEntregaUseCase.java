package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.PedidoService;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FinalizarEntregaUseCase {

    private final EntregaRepositoryImpl entregaRepository;
    private final PedidoService pedidoService;

    public FinalizarEntregaUseCase(EntregaRepositoryImpl entregaRepository,
                                   PedidoService pedidoService) {
        this.entregaRepository = entregaRepository;
        this.pedidoService = pedidoService;
    }

    public EntregaDTO executar(UUID entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new ControllerNotFoundException("Entrega não encontrado."));

        entrega.atualizarStatus(StatusEntrega.ENTREGUE);
        Entrega entregaAtualizada = entregaRepository.save(entrega);

        if (!pedidoService.atualizarStatusPedido(entregaAtualizada.getPedidoId().toString(), "CONCLUIR_PEDIDO")){
            entrega.atualizarStatus(StatusEntrega.EM_TRANSITO);
            throw new ControllerMessagingException("Problema com MS de Gestão de Pedido ao tentar atualizar o pedido: "
                    + entregaAtualizada.getPedidoId().toString() + " para o status: FINALIZADO" );
        }

        return EntregaMapper.toDTO(entregaAtualizada);
    }
}
