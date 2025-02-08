package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CancelarEntregaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CriarEntregaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.event.PedidoCanceladoEvent;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.event.PedidoCriadoEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class PedidoConsumer {
    private final CriarEntregaUseCase criarEntregaUseCase;
    private final CancelarEntregaUseCase cancelarEntregaUseCase;

    public PedidoConsumer(CriarEntregaUseCase criarEntregaUseCase,
                          CancelarEntregaUseCase cancelarEntregaUseCase){
        this.criarEntregaUseCase = criarEntregaUseCase;
        this.cancelarEntregaUseCase = cancelarEntregaUseCase;
    }

    @Bean
    public Consumer<PedidoCriadoEvent> processarPedidoCriado() {
        return event -> {
            EntregaDTO entregaDTO = new EntregaDTO(
                    UUID.randomUUID(),
                    event.pedidoId(),
                    null,
                    event.endereco(),
                    StatusEntrega.PENDENTE,
                    LocalDateTime.now().plusDays(14),
                    null,
                    UUID.randomUUID().toString()
            );

            criarEntregaUseCase.executar(entregaDTO);

        };
    }

    @Bean
    public Consumer<PedidoCanceladoEvent> processarPedidoCancelado() {
        return event -> {
            cancelarEntregaUseCase.executar(event.pedidoId());
        };
    }
}
