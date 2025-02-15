package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CriarEntregaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.event.PedidoCriadoEvent;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;

@Component
public class PedidoConsumer {
    private final CriarEntregaUseCase criarEntregaUseCase;

    public PedidoConsumer(CriarEntregaUseCase criarEntregaUseCase){
        this.criarEntregaUseCase = criarEntregaUseCase;
    }

    @Bean
    public Consumer<PedidoCriadoEvent> processarPedidoCriado() {
        return event -> {
            try {
                if (event.pedidoId() == null || event.endereco() == null) {
                    throw new ControllerMessagingException("PedidoId ou Endereço invalido.");
                }

                EntregaDTO entregaDTO = new EntregaDTO(
                        null,
                        event.pedidoId(),
                        null,
                        event.endereco(),
                        StatusEntrega.PENDENTE,
                        LocalDateTime.now().plusDays(14),
                        null,
                        UUID.randomUUID().toString()
                );

                criarEntregaUseCase.executar(entregaDTO);

            }catch (Exception e){
                throw new ControllerMessagingException(e.getMessage());
            }

        };
    }
}
