package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.RotaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.RotaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.RotaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalcularRotaUseCaseTest {

    @Mock
    private RotaService rotaService;

    @Mock
    private RotaRepositoryImpl rotaRepository;

    @Mock
    private EntregaRepositoryImpl entregaRepository;

    @Mock
    private RotaMapper rotaMapper;

    @InjectMocks
    private CalcularRotaUseCase calcularRotaUseCase;

    private UUID entregaId;
    private String origem;
    private String destino;
    private Rota rota;
    private RotaDTO rotaDTO;

    @BeforeEach
    void setUp() {
        entregaId = UUID.randomUUID();
        origem = "Origem";
        destino = "Destino";
        rota = new Rota();
        rotaDTO = new RotaDTO();

        rota = new Rota(); // Inicialize a rota corretamente
        rotaDTO = new RotaDTO();
    }

    @Test
    void calcularMelhorRota_EntregaNotFound() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> {
            calcularRotaUseCase.calcularMelhorRota(entregaId, origem, destino);
        });

        verify(entregaRepository, times(1)).findById(entregaId);
        verifyNoMoreInteractions(entregaRepository, rotaService, rotaRepository);
    }

    @Test
    void calcularMelhorRota_RotaCalculadaNull() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.of(new Entrega()));
        when(rotaService.calcularMelhorRota(entregaId, origem, destino)).thenReturn(null);

        assertThrows(ControllerMessagingException.class, () -> {
            calcularRotaUseCase.calcularMelhorRota(entregaId, origem, destino);
        });

        verify(entregaRepository, times(1)).findById(entregaId);
        verify(rotaService, times(1)).calcularMelhorRota(entregaId, origem, destino);
        verifyNoMoreInteractions(entregaRepository, rotaService, rotaRepository);
    }

    @Test
    void calcularMelhorRota_Success() {
        when(entregaRepository.findById(entregaId)).thenReturn(Optional.of(new Entrega()));
        when(rotaService.calcularMelhorRota(entregaId, origem, destino)).thenReturn(rota);
        when(rotaRepository.save(rota)).thenReturn(rota);

        
        RotaDTO dtoEsperado = RotaMapper.toDTO(rota);

        RotaDTO result = calcularRotaUseCase.calcularMelhorRota(entregaId, origem, destino);

        assertNotNull(result);
        assertEquals(dtoEsperado, result);

        verify(entregaRepository, times(1)).findById(entregaId);
        verify(rotaService, times(1)).calcularMelhorRota(entregaId, origem, destino);
        verify(rotaRepository, times(1)).save(rota);
    }
}


