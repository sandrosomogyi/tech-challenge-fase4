package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregadorRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.EntregaEventPublisher;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;
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
class AtribuirEntregadorUseCaseTest {

    @Mock
    private EntregaRepositoryImpl entregaRepository;

    @Mock
    private EntregadorRepositoryImpl entregadorRepository;

    @Mock
    private EntregaEventPublisher eventPublisher;

    @InjectMocks
    private AtribuirEntregadorUseCase atribuirEntregadorUseCase;

    private UUID entregaId;
    private UUID entregadorId;
    private Entrega entrega;

    @BeforeEach
    void setUp() {
        entregaId = UUID.randomUUID();
        entregadorId = UUID.randomUUID();
        entrega = new Entrega();
        entrega.setId(entregaId);
    }

    @Test
    void executar_shouldAssignEntregadorAndPublishEvent() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.of(entrega));
        when(entregadorRepository.findById(entregadorId)).thenReturn(Optional.of(new Entregador())); // Corrigido para retornar Optional<Entregador>
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);

        EntregaDTO entregaDTO = atribuirEntregadorUseCase.executar(entregaId, entregadorId);

        assertNotNull(entregaDTO);
        assertEquals(entregadorId, entrega.getEntregadorId());
    }

    @Test
    void executar_shouldThrowExceptionWhenEntregaNotFound() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> atribuirEntregadorUseCase.executar(entregaId, entregadorId));
    }

    @Test
    void executar_shouldThrowExceptionWhenEntregadorNotFound() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.of(entrega));
        when(entregadorRepository.findById(entregadorId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> atribuirEntregadorUseCase.executar(entregaId, entregadorId));
    }
}