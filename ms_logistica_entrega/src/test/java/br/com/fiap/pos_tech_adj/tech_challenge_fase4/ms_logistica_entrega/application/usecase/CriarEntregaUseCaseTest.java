package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarEntregaUseCaseTest {

    @Mock
    private EntregaRepositoryImpl entregaRepository; 

    @InjectMocks
    private CriarEntregaUseCase criarEntregaUseCase; 

    @Test
    public void testExecutar() {
        
        EntregaDTO entregaDTO = new EntregaDTO();
        Entrega entrega = new Entrega();
        Entrega entregaSalva = new Entrega();
        
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entregaSalva);
        
        try (MockedStatic<EntregaMapper> mockedMapper = mockStatic(EntregaMapper.class)) {
            mockedMapper.when(() -> EntregaMapper.toEntity(entregaDTO)).thenReturn(entrega);
            mockedMapper.when(() -> EntregaMapper.toDTO(entregaSalva)).thenReturn(entregaDTO);
            
            EntregaDTO result = criarEntregaUseCase.executar(entregaDTO);
            
            assertNotNull(result);
            assertEquals(entregaDTO, result);
        }
       
        verify(entregaRepository, times(1)).save(any(Entrega.class));
    }
    @Test
    public void testSaveCalledWithCorrectEntrega() {
        EntregaDTO entregaDTO = new EntregaDTO();
        Entrega entrega = new Entrega();
        Entrega entregaSalva = new Entrega();

        when(entregaRepository.save(any(Entrega.class))).thenReturn(entregaSalva);

        try (MockedStatic<EntregaMapper> mockedMapper = mockStatic(EntregaMapper.class)) {
            mockedMapper.when(() -> EntregaMapper.toEntity(entregaDTO)).thenReturn(entrega);
            mockedMapper.when(() -> EntregaMapper.toDTO(entregaSalva)).thenReturn(entregaDTO);

            criarEntregaUseCase.executar(entregaDTO);

            verify(entregaRepository).save(entrega);
        }
    }

    @Test
    public void testToEntityCalledWithCorrectDTO() {
        EntregaDTO entregaDTO = new EntregaDTO();
        Entrega entrega = new Entrega();

        try (MockedStatic<EntregaMapper> mockedMapper = mockStatic(EntregaMapper.class)) {
            mockedMapper.when(() -> EntregaMapper.toEntity(entregaDTO)).thenReturn(entrega);

            criarEntregaUseCase.executar(entregaDTO);

            mockedMapper.verify(() -> EntregaMapper.toEntity(entregaDTO));
        }
    }

    @Test
    public void testToDTOCalledWithSavedEntrega() {
        EntregaDTO entregaDTO = new EntregaDTO();
        Entrega entrega = new Entrega();
        Entrega entregaSalva = new Entrega();

        when(entregaRepository.save(any(Entrega.class))).thenReturn(entregaSalva);

        try (MockedStatic<EntregaMapper> mockedMapper = mockStatic(EntregaMapper.class)) {
            mockedMapper.when(() -> EntregaMapper.toEntity(entregaDTO)).thenReturn(entrega);
            mockedMapper.when(() -> EntregaMapper.toDTO(entregaSalva)).thenReturn(entregaDTO);

            criarEntregaUseCase.executar(entregaDTO);

            mockedMapper.verify(() -> EntregaMapper.toDTO(entregaSalva));
        }
    }


}
