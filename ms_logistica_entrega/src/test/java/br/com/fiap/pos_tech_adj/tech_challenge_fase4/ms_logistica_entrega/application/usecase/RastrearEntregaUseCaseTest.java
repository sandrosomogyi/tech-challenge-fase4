package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.RotaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.RotaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.RotaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RastrearEntregaUseCaseTest {

    @Mock
    private RotaRepositoryImpl rotaRepository;

    @Mock
    private RotaRepository rotaRepos;


    @Mock
    private EntregaRepositoryImpl entregaRepository;

    @InjectMocks
    private RastrearEntregaUseCase rastrearEntregaUseCase;

    private UUID entregaId;
    private String codigoRastreio;
    private Entrega entrega;
    private Rota rota;
    private RotaDTO rotaDTO;

    @BeforeEach
    void setUp() {
        entregaId = UUID.randomUUID();
        codigoRastreio = "123456";
        entrega = new Entrega();
        entrega.setId(entregaId);
        rota = new Rota();
        rotaDTO = new RotaDTO();
    }

    @Test
    void rastrearRotasPorEntregaId_ShouldReturnRotaDTOList_WhenRotasExist() {
        when(rotaRepository.findByEntregaId(entregaId)).thenReturn(List.of(rota));

        try (MockedStatic<RotaMapper> mockedMapper = mockStatic(RotaMapper.class)) {
            mockedMapper.when(() -> RotaMapper.toDTO(rota)).thenReturn(rotaDTO);

            List<RotaDTO> result = rastrearEntregaUseCase.rastrearRotasPorEntregaId(entregaId);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(rotaRepository, times(1)).findByEntregaId(entregaId);
        }
    }

    @Test
    void rastrearRotasPorEntregaId_ShouldThrowControllerMessagingException_WhenNoRotasExist() {
        when(rotaRepository.findByEntregaId(entregaId)).thenReturn(Collections.emptyList());

        assertThrows(ControllerMessagingException.class, () -> rastrearEntregaUseCase.rastrearRotasPorEntregaId(entregaId));
        verify(rotaRepository, times(1)).findByEntregaId(entregaId);
    }

    
    @Test
    void rastrearRotasPorCodigoRastreio_ShouldThrowControllerNotFoundException_WhenEntregaDoesNotExist() {
        when(entregaRepository.findByCodigoRastreio(codigoRastreio)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> rastrearEntregaUseCase.rastrearRotasPorCodigoRastreio(codigoRastreio));
        verify(entregaRepository, times(1)).findByCodigoRastreio(codigoRastreio);
    }

    @Test
    void rastrearRotasPorCodigoRastreio_ShouldReturnRotaDTOList_WhenEntregaExists() {
        when(entregaRepository.findByCodigoRastreio(codigoRastreio)).thenReturn(Optional.of(entrega));
        when(rotaRepository.findByEntregaId(entregaId)).thenReturn(List.of(rota));

        try (MockedStatic<RotaMapper> mockedMapper = mockStatic(RotaMapper.class)) {
            mockedMapper.when(() -> RotaMapper.toDTO(rota)).thenReturn(rotaDTO);

            List<RotaDTO> result = rastrearEntregaUseCase.rastrearRotasPorCodigoRastreio(codigoRastreio);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(entregaRepository, times(1)).findByCodigoRastreio(codigoRastreio);
            verify(rotaRepository, times(1)).findByEntregaId(entregaId);
        }
    }
}