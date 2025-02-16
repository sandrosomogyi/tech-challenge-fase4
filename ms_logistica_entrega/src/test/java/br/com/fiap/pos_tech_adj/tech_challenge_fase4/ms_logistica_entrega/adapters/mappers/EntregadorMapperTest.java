package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

public class EntregadorMapperTest {

    private EntregadorMapper entregadorMapper;

    @BeforeEach
    void setUp() {
        entregadorMapper = new EntregadorMapper();
    }


    @Test
    public void testToEntity() {
        EntregadorDTO dto = new EntregadorDTO();
        dto.setId(UUID.randomUUID());
        dto.setNome("Entregador Teste");
        dto.setTelefone("11999999999");
        dto.setVeiculo("Moto");

        Entregador entity = EntregadorMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNome(), entity.getNome());
        assertEquals(dto.getTelefone(), entity.getTelefone());
        assertEquals(dto.getVeiculo(), entity.getVeiculo());
    }

    @Test
    public void testToDTO() {
        Entregador entity = new Entregador();
        entity.setId(UUID.randomUUID());
        entity.setNome("Entregador Teste");
        entity.setTelefone("11999999999");
        entity.setVeiculo("Moto");
        EntregadorDTO dto = EntregadorMapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNome(), dto.getNome());
        assertEquals(entity.getTelefone(), dto.getTelefone());
        assertEquals(entity.getVeiculo(), dto.getVeiculo());
        
    }
}