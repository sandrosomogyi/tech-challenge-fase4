package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.ClienteService;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.EntregaService;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AtualizarStatusPedidoUseCase {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final ClienteService clienteService;
    private final EntregaService entregaService;

    public AtualizarStatusPedidoUseCase(PedidoJpaRepository pedidoJpaRepository,
            ClienteService clienteService,
            EntregaService entregaService) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.clienteService = clienteService;
        this.entregaService = entregaService;
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

                ClienteDTO cliente = clienteService.getClienteById(pedidoExistente.getClienteId());

                EntregaDTO entrega = new EntregaDTO(
                        UUID.randomUUID(),
                        pedidoExistente.getId(),
                        null,
                        cliente.getEndereco(),
                        "PENDENTE",
                        LocalDateTime.now().plusDays(14),
                        null,
                        UUID.randomUUID().toString()
                        );

                EntregaDTO entregaSalva = entregaService.criarEntrega(entrega);

                if (entregaSalva.getId().toString().isEmpty()){
                    throw new ControllerMessagingException("Problema ao criar entrega.");
                }

                break;

            case CONCLUIDO:
                throw new ControllerMessagingException("Pedido já foi CONCLUIDO e não pode ter o status alterado.");

            default:
                break;
        }


        pedidoExistente.setStatus(PedidoStatus.valueOf(status));

        // Salva a atualização no banco de dados
        return pedidoJpaRepository.save(pedidoExistente);
    }
}
