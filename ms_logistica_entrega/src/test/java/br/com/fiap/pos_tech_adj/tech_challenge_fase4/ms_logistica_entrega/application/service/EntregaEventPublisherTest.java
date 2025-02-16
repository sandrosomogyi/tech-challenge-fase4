package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.event.EntregaAtualizadaEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.eq;

public class EntregaEventPublisherTest {

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private EntregaEventPublisher entregaEventPublisher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublicarEntregaAtualizada() {
        UUID pedidoId = UUID.randomUUID();
        String statusEntrega = "DELIVERED";

        entregaEventPublisher.publicarEntregaAtualizada(pedidoId, statusEntrega);

        ArgumentCaptor<EntregaAtualizadaEvent> eventCaptor = ArgumentCaptor.forClass(EntregaAtualizadaEvent.class);
        verify(streamBridge).send(eq("entregaAtualizada-out-0"), eventCaptor.capture());

        EntregaAtualizadaEvent capturedEvent = eventCaptor.getValue();
        assertEquals(pedidoId, capturedEvent.pedidoId());
        assertEquals(statusEntrega, capturedEvent.statusEntrega());
    }

    @Test
    public void testPublicarEntregaAtualizadaComStatusDiferente() {
        UUID pedidoId = UUID.randomUUID();
        String statusEntrega = "IN_TRANSIT";

        entregaEventPublisher.publicarEntregaAtualizada(pedidoId, statusEntrega);

        ArgumentCaptor<EntregaAtualizadaEvent> eventCaptor = ArgumentCaptor.forClass(EntregaAtualizadaEvent.class);
        verify(streamBridge).send(eq("entregaAtualizada-out-0"), eventCaptor.capture());

        EntregaAtualizadaEvent capturedEvent = eventCaptor.getValue();
        assertEquals(pedidoId, capturedEvent.pedidoId());
        assertEquals(statusEntrega, capturedEvent.statusEntrega());
    }

    @Test
    public void testPublicarEntregaAtualizadaComStatusVazio() {
        UUID pedidoId = UUID.randomUUID();
        String statusEntrega = "";

        entregaEventPublisher.publicarEntregaAtualizada(pedidoId, statusEntrega);

        ArgumentCaptor<EntregaAtualizadaEvent> eventCaptor = ArgumentCaptor.forClass(EntregaAtualizadaEvent.class);
        verify(streamBridge).send(eq("entregaAtualizada-out-0"), eventCaptor.capture());

        EntregaAtualizadaEvent capturedEvent = eventCaptor.getValue();
        assertEquals(pedidoId, capturedEvent.pedidoId());
        assertEquals(statusEntrega, capturedEvent.statusEntrega());
    }
}