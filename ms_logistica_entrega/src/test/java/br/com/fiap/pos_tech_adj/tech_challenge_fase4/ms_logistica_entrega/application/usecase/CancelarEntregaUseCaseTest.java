package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.EntregaEventPublisher;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelarEntregaUseCaseTest {

    @Mock
    private EntregaRepositoryImpl entregaRepository;

    @Mock
    private EntregaEventPublisher eventPublisher;

    @InjectMocks
    private CancelarEntregaUseCase cancelarEntregaUseCase;

    private UUID pedidoId;
    private Entrega entrega;

    @BeforeEach
    void setUp() {
        pedidoId = UUID.randomUUID();
        entrega = new Entrega();
        entrega.setId(pedidoId);
        entrega.setStatus(StatusEntrega.PENDENTE);
    }

    @Test
    void executar_shouldCancelEntrega_whenEntregaExists() {
        when(entregaRepository.findByPedidoId(pedidoId)).thenReturn(Optional.of(entrega));
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);

        EntregaDTO entregaDTO = cancelarEntregaUseCase.executar(pedidoId);

        assertNotNull(entregaDTO);
        assertEquals(StatusEntrega.CANCELADO, entrega.getStatus());
        verify(entregaRepository).findByPedidoId(pedidoId);
        verify(entregaRepository).save(entrega);
    }

    @Test
    void executar_shouldThrowControllerNotFoundException_whenEntregaDoesNotExist() {
        when(entregaRepository.findByPedidoId(pedidoId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> cancelarEntregaUseCase.executar(pedidoId));

        verify(entregaRepository).findByPedidoId(pedidoId);
        verify(entregaRepository, never()).save(any(Entrega.class));
    }    
}