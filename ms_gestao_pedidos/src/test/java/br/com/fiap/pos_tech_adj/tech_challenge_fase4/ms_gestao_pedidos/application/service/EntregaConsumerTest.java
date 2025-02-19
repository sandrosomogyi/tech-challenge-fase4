package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.AtualizarStatusPedidoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.event.EntregaAtualizadaEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.util.function.Consumer;
import static org.mockito.Mockito.*;

class EntregaConsumerTest {

    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private EntregaConsumer entregaConsumer;
    private Consumer<EntregaAtualizadaEvent> consumer;

    @BeforeEach
    void setUp() {
        atualizarStatusPedidoUseCase = mock(AtualizarStatusPedidoUseCase.class);
        entregaConsumer = new EntregaConsumer(atualizarStatusPedidoUseCase);
        consumer = entregaConsumer.processarEntregaAtualizada();
    }

    @Test
    void deveProcessarEntregaAtualizada() {
        // Given
        UUID pedidoId = UUID.randomUUID();
        String statusEntrega = "ENTREGUE";
        EntregaAtualizadaEvent event = new EntregaAtualizadaEvent(pedidoId, statusEntrega);

        // When
        consumer.accept(event);

        // Then
        verify(atualizarStatusPedidoUseCase, times(1)).executar(pedidoId, statusEntrega);
    }
}
