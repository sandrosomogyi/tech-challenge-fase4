package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregadorRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenciarEntregadorUseCaseTest {

    @Mock
    private EntregadorRepositoryImpl entregadorRepository;

    @InjectMocks
    private GerenciarEntregadorUseCase gerenciarEntregadorUseCase;

    private EntregadorDTO entregadorDTO;
    private Entregador entregador;
    private UUID entregadorId;

    @BeforeEach
    void setUp() {
        entregadorId = UUID.randomUUID();
        entregadorDTO = new EntregadorDTO();
        entregadorDTO.setId(entregadorId);
        entregador = new Entregador();
        entregador.setId(entregadorId);
    }

    @Test
    void cadastrarEntregador() {
        when(entregadorRepository.save(any(Entregador.class))).thenReturn(entregador);

        EntregadorDTO result = gerenciarEntregadorUseCase.cadastrarEntregador(entregadorDTO);

        assertNotNull(result);
        assertEquals(entregadorId, result.getId());
        verify(entregadorRepository, times(1)).save(any(Entregador.class));
    }

    @Test
    void buscarEntregadorPorId() {
        when(entregadorRepository.findById(entregadorId)).thenReturn(Optional.of(entregador));

        Optional<EntregadorDTO> result = gerenciarEntregadorUseCase.buscarEntregadorPorId(entregadorId);

        assertTrue(result.isPresent());
        assertEquals(entregadorId, result.get().getId());
        verify(entregadorRepository, times(1)).findById(entregadorId);
    }

    @Test
    void buscarEntregadores() {
        when(entregadorRepository.findAll()).thenReturn(List.of(entregador));

        List<EntregadorDTO> result = gerenciarEntregadorUseCase.buscarEntregadores();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(entregadorId, result.get(0).getId());
        verify(entregadorRepository, times(1)).findAll();
    }

    @Test
    void atualizarEntregador() {
        when(entregadorRepository.findById(entregadorId)).thenReturn(Optional.of(entregador));
        when(entregadorRepository.save(any(Entregador.class))).thenReturn(entregador);

        Optional<EntregadorDTO> result = gerenciarEntregadorUseCase.atualizarEntregador(entregadorId, entregadorDTO);

        assertTrue(result.isPresent());
        assertEquals(entregadorId, result.get().getId());
        verify(entregadorRepository, times(1)).findById(entregadorId);
        verify(entregadorRepository, times(1)).save(any(Entregador.class));
    }

    @Test
    void atualizarEntregadorNotFound() {
        when(entregadorRepository.findById(entregadorId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> {
            gerenciarEntregadorUseCase.atualizarEntregador(entregadorId, entregadorDTO);
        });

        verify(entregadorRepository, times(1)).findById(entregadorId);
        verify(entregadorRepository, times(0)).save(any(Entregador.class));
    }

    @Test
    void removerEntregador() {
        when(entregadorRepository.findById(entregadorId)).thenReturn(Optional.of(entregador));

        gerenciarEntregadorUseCase.removerEntregador(entregadorId);

        verify(entregadorRepository, times(1)).findById(entregadorId);
        verify(entregadorRepository, times(1)).deleteById(entregadorId);
    }

    @Test
    void removerEntregadorNotFound() {
        when(entregadorRepository.findById(entregadorId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> {
            gerenciarEntregadorUseCase.removerEntregador(entregadorId);
        });

        verify(entregadorRepository, times(1)).findById(entregadorId);
        verify(entregadorRepository, times(0)).deleteById(entregadorId);
    }
}