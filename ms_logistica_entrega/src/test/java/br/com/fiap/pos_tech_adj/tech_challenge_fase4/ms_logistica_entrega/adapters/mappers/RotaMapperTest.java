package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RotaMapperTest {

    @InjectMocks
    private RotaMapper rotaMapper;

    private Rota rota;
    private RotaDTO rotaDTO;

    @BeforeEach
    void setUp() {
        rota = new Rota();
        rota.setId(UUID.randomUUID());
        rota.setEntregaId(UUID.randomUUID());
        rota.setPontoPartida("Rua A, 123");
        rota.setDestino("Rua B, 456");
        rota.setDistancia(5000);
        rota.setTempoEstimado(600);
        rota.setDataSaida(LocalDateTime.now());

        rotaDTO = new RotaDTO();
        rotaDTO.setId(rota.getId());
        rotaDTO.setEntregaId(rota.getEntregaId());
        rotaDTO.setPontoPartida(rota.getPontoPartida());
        rotaDTO.setDestino(rota.getDestino());
        rotaDTO.setDistancia(rota.getDistancia());
        rotaDTO.setTempoEstimado(rota.getTempoEstimado());
        rotaDTO.setDataSaida(rota.getDataSaida());
    }

    @Test
    void testToEntity() {
        Rota entity = rotaMapper.toEntity(rotaDTO);

        assertNotNull(entity);
        assertEquals(rotaDTO.getId(), entity.getId());
        assertEquals(rotaDTO.getEntregaId(), entity.getEntregaId());
        assertEquals(rotaDTO.getPontoPartida(), entity.getPontoPartida());
        assertEquals(rotaDTO.getDestino(), entity.getDestino());
        assertEquals(rotaDTO.getDistancia(), entity.getDistancia());
        assertEquals(rotaDTO.getTempoEstimado(), entity.getTempoEstimado());
        assertEquals(rotaDTO.getDataSaida(), entity.getDataSaida());
    }

    @Test
    void testToDTO() {
        RotaDTO dto = rotaMapper.toDTO(rota);

        assertNotNull(dto);
        assertEquals(rota.getId(), dto.getId());
        assertEquals(rota.getEntregaId(), dto.getEntregaId());
        assertEquals(rota.getPontoPartida(), dto.getPontoPartida());
        assertEquals(rota.getDestino(), dto.getDestino());
        assertEquals(rota.getDistancia(), dto.getDistancia());
        assertEquals(rota.getTempoEstimado(), dto.getTempoEstimado());
        assertEquals(rota.getDataSaida(), dto.getDataSaida());
    }
}