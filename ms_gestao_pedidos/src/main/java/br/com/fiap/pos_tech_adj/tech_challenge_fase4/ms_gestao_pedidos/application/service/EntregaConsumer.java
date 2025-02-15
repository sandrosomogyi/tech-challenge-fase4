package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.AtualizarStatusPedidoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.event.EntregaAtualizadaEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class EntregaConsumer{

    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    public EntregaConsumer (AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase){
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    @Bean
    public Consumer<EntregaAtualizadaEvent> processarEntregaAtualizada() {
        return event -> {
            atualizarStatusPedidoUseCase.executar(event.pedidoId(), event.statusEntrega());
        };
    }
}
