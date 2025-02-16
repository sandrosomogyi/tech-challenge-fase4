package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarEntregaUseCaseTest {

    @Mock
    private EntregaRepositoryImpl entregaRepository;

    @InjectMocks
    private BuscarEntregaUseCase buscarEntregaUseCase;

    private UUID entregaId;
    private EntregaDTO entregaDTO;

    @BeforeEach
    void setUp() {
        entregaId = UUID.randomUUID();
        entregaDTO = new EntregaDTO();
    }

    @Test
    void buscarEntregaPorId_shouldReturnEntregaDTO_whenEntregaExists() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.of(new EntregaMapper().toEntity(entregaDTO)));

        Optional<EntregaDTO> result = buscarEntregaUseCase.buscarEntregaPorId(entregaId);

        assertTrue(result.isPresent());
        assertEquals(entregaDTO, result.get());
        verify(entregaRepository, times(1)).findById(entregaId);
    }

    @Test
    void buscarEntregaPorId_shouldReturnEmpty_whenEntregaDoesNotExist() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.empty());

        Optional<EntregaDTO> result = buscarEntregaUseCase.buscarEntregaPorId(entregaId);

        assertFalse(result.isPresent());
        verify(entregaRepository, times(1)).findById(entregaId);
    }

    @Test
    void buscarEntregas_shouldReturnListOfEntregaDTO() {
        when(entregaRepository.findAll()).thenReturn(Collections.singletonList(new EntregaMapper().toEntity(entregaDTO)));

        List<EntregaDTO> result = buscarEntregaUseCase.buscarEntregas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(entregaDTO, result.get(0));
        verify(entregaRepository, times(1)).findAll();
    }

    @Test
    void buscarEntregas_shouldThrowRuntimeException_whenExceptionOccurs() {
        when(entregaRepository.findAll()).thenThrow(new RuntimeException("Erro ao listar entregas"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> buscarEntregaUseCase.buscarEntregas());

        assertEquals("Erro ao listar entregas", exception.getMessage());
        verify(entregaRepository, times(1)).findAll();
    }
}