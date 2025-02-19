package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.event.PedidoCriadoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

class PedidoEventPublisherTest {

    private StreamBridge streamBridge;
    private PedidoEventPublisher pedidoEventPublisher;

    @BeforeEach
    void setUp() {
        streamBridge = mock(StreamBridge.class);
        pedidoEventPublisher = new PedidoEventPublisher(streamBridge);
    }

    @Test
    void devePublicarPedidoCriadoComSucesso() {
        UUID pedidoId = UUID.randomUUID();
        String endereco = "Rua Teste, 123";

        pedidoEventPublisher.publicarPedidoCriado(pedidoId, endereco);

        ArgumentCaptor<PedidoCriadoEvent> eventCaptor = ArgumentCaptor.forClass(PedidoCriadoEvent.class);
        verify(streamBridge).send(eq("pedidoCriado-out-0"), eventCaptor.capture());

        PedidoCriadoEvent capturedEvent = eventCaptor.getValue();
        assertEquals(pedidoId, capturedEvent.pedidoId());
        assertEquals(endereco, capturedEvent.endereco());
    }
}