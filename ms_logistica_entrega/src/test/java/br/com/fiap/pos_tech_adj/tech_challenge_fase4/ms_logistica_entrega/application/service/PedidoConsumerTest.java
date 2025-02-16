package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CriarEntregaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.event.PedidoCriadoEvent;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoConsumerTest {

    @Mock
    private CriarEntregaUseCase criarEntregaUseCase;

    @InjectMocks
    private PedidoConsumer pedidoConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessarPedidoCriado_Success() {
        PedidoCriadoEvent event = mock(PedidoCriadoEvent.class);
        when(event.pedidoId()).thenReturn(UUID.randomUUID());
        when(event.endereco()).thenReturn("Rua Teste, 123");

        Consumer<PedidoCriadoEvent> consumer = pedidoConsumer.processarPedidoCriado();
        consumer.accept(event);

        ArgumentCaptor<EntregaDTO> entregaCaptor = ArgumentCaptor.forClass(EntregaDTO.class);
        verify(criarEntregaUseCase).executar(entregaCaptor.capture());

        EntregaDTO entregaDTO = entregaCaptor.getValue();
        assertNotNull(entregaDTO);
        assertEquals(event.pedidoId(), entregaDTO.getPedidoId());
        assertEquals(event.endereco(), entregaDTO.getEnderecoDestino());
        assertEquals(StatusEntrega.PENDENTE, entregaDTO.getStatus());
        assertNotNull(entregaDTO.getDataHoraPrevista());
        assertNotNull(entregaDTO.getCodigoRastreio());
    }

    @Test
    void testProcessarPedidoCriado_InvalidEvent() {
        PedidoCriadoEvent event = mock(PedidoCriadoEvent.class);
        when(event.pedidoId()).thenReturn(null);
        when(event.endereco()).thenReturn(null);

        Consumer<PedidoCriadoEvent> consumer = pedidoConsumer.processarPedidoCriado();

        ControllerMessagingException exception = assertThrows(ControllerMessagingException.class, () -> {
            consumer.accept(event);
        });

        assertEquals("PedidoId ou Endereço invalido.", exception.getMessage());
    }

    @Test
    void testProcessarPedidoCriado_ExceptionThrown() {
        PedidoCriadoEvent event = mock(PedidoCriadoEvent.class);
        when(event.pedidoId()).thenReturn(UUID.randomUUID());
        when(event.endereco()).thenReturn("Rua Teste, 123");

        doThrow(new RuntimeException("Test Exception")).when(criarEntregaUseCase).executar(any(EntregaDTO.class));

        Consumer<PedidoCriadoEvent> consumer = pedidoConsumer.processarPedidoCriado();

        ControllerMessagingException exception = assertThrows(ControllerMessagingException.class, () -> {
            consumer.accept(event);
        });

        assertEquals("Test Exception", exception.getMessage());
    }
}