package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.PedidoEventPublisher;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarStatusPedidoUseCase {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoEventPublisher eventPublisher;

    public AtualizarStatusPedidoUseCase(PedidoJpaRepository pedidoJpaRepository,
                                        PedidoEventPublisher eventPublisher) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.eventPublisher = eventPublisher;
    }

    public Pedido executar(UUID pedidoId, String status) {
        // Verifica se o pedido existe no banco de dados
        Pedido pedidoExistente = pedidoJpaRepository.findById(pedidoId)
                .orElseThrow(() -> new ControllerNotFoundException("Pedido não encontrado."));

        if (pedidoExistente.getStatus() == PedidoStatus.CANCELADO) {
            throw new ControllerMessagingException("Pedido CANCELADO não pode ter o status alterado.");
        }

        switch (pedidoExistente.getStatus()) {
            case PENDENTE:
                if (status.equalsIgnoreCase(PedidoStatus.CONCLUIDO.toString())) {
                    throw new ControllerMessagingException("Pedido precisa passar por EM_TRANSITO antes de ser CONCLUIDO.");
                }
                break;

            case EM_TRANSITO:
                if (status.equalsIgnoreCase(PedidoStatus.PENDENTE.toString())) {
                    throw new ControllerMessagingException("Pedido não pode voltar de EM_TRANSITO para PENDENTE.");
                }
                break;

            case CONCLUIDO:
                throw new ControllerMessagingException("Pedido já foi CONCLUIDO e não pode ter o status alterado.");

            default:
                break;
        }

        pedidoExistente.setStatus(PedidoStatus.valueOf(status));

        Pedido pedidoSalvo = pedidoJpaRepository.save(pedidoExistente);

        if (pedidoSalvo.getStatus() == PedidoStatus.CANCELADO) {
            // Publicar evento no Kafka
            eventPublisher.publicarPedidoCancelado(pedidoSalvo.getId());
        }

        // Salva a atualização no banco de dados
        return pedidoSalvo;
    }
}
