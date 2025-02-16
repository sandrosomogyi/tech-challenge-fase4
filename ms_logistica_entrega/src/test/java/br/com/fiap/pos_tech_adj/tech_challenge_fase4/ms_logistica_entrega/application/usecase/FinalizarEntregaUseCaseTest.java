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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinalizarEntregaUseCaseTest {

    @Mock
    private EntregaRepositoryImpl entregaRepository;

    @Mock
    private EntregaEventPublisher eventPublisher;

    @InjectMocks
    private FinalizarEntregaUseCase finalizarEntregaUseCase;

    private UUID entregaId;
    private Entrega entrega;

    @BeforeEach
    void setUp() {
        entregaId = UUID.randomUUID();
        entrega = new Entrega();
        entrega.setId(entregaId);
        entrega.setStatus(StatusEntrega.PENDENTE);
    }

    @Test
    void executar_shouldFinalizeEntrega_whenEntregaExists() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.of(entrega));
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);

        EntregaDTO entregaDTO = finalizarEntregaUseCase.executar(entregaId);

        assertNotNull(entregaDTO);
        assertEquals(StatusEntrega.CONCLUIDA, entrega.getStatus());
        verify(entregaRepository).findById(entregaId);
        verify(entregaRepository).save(entrega);
        verify(eventPublisher).publicarEntregaAtualizada(entrega.getPedidoId(), "CONCLUIDO");
    }

    @Test
    void executar_shouldThrowControllerNotFoundException_whenEntregaDoesNotExist() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> finalizarEntregaUseCase.executar(entregaId));

        verify(entregaRepository).findById(entregaId);
        verify(entregaRepository, never()).save(any(Entrega.class));
        verify(eventPublisher, never()).publicarEntregaAtualizada(any(UUID.class), anyString());
    }
}