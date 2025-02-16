package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

public class EntregaMapperTest {

    private EntregaMapper entregaMapper;

    @BeforeEach
    void setUp() {
        entregaMapper = new EntregaMapper();
    }

    @Test
    public void testToEntity() {
        EntregaDTO dto = new EntregaDTO();
        dto.setId(UUID.randomUUID());
        dto.setPedidoId(UUID.randomUUID());
        dto.setEntregadorId(UUID.randomUUID());
        dto.setEnderecoDestino("Rua 2");
        dto.setStatus(StatusEntrega.PENDENTE);
        dto.setDataHoraPrevista(LocalDateTime.now());
        dto.setDataHoraConclusao(LocalDateTime.now());
        dto.setCodigoRastreio("123456");
        Entrega entity = EntregaMapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getPedidoId(), entity.getPedidoId());
        assertEquals(dto.getEntregadorId(), entity.getEntregadorId());
        assertEquals(dto.getEnderecoDestino(), entity.getEnderecoDestino());
        assertEquals(dto.getStatus(), entity.getStatus());
        assertEquals(dto.getDataHoraPrevista(), entity.getDataHoraPrevista());
        assertEquals(dto.getDataHoraConclusao(), entity.getDataHoraConclusao());
        assertEquals(dto.getCodigoRastreio(), entity.getCodigoRastreio());
    }

    @Test
    public void testToDTO() {
        Entrega entity = new Entrega();
        entity.setId(UUID.randomUUID());
        entity.setPedidoId(UUID.randomUUID());
        entity.setEntregadorId(UUID.randomUUID());
        entity.setEnderecoDestino("Rua 2");
        entity.setStatus(StatusEntrega.PENDENTE);
        entity.setDataHoraPrevista(LocalDateTime.now());
        entity.setDataHoraConclusao(LocalDateTime.now());
        entity.setCodigoRastreio("123456");
        EntregaDTO dto = EntregaMapper.toDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getPedidoId(), dto.getPedidoId());
        assertEquals(entity.getEntregadorId(), dto.getEntregadorId());
        assertEquals(entity.getEnderecoDestino(), dto.getEnderecoDestino());
        assertEquals(entity.getStatus(), dto.getStatus());
        assertEquals(entity.getDataHoraPrevista(), dto.getDataHoraPrevista());
        assertEquals(entity.getDataHoraConclusao(), dto.getDataHoraConclusao());
        assertEquals(entity.getCodigoRastreio(), dto.getCodigoRastreio());
    }

}