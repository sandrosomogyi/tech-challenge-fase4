package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.PedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;


public class PedidoMapperTest {

    private final PedidoMapper mapper = new PedidoMapper();

    @Test
    void testToDTO() {
        
        UUID id = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();
        LocalDateTime dataCriacao = LocalDateTime.now();
        BigDecimal total = new BigDecimal("100.00");
        PedidoStatus status = PedidoStatus.PENDENTE;
        
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setClienteId(clienteId);
        pedido.setItens(Collections.emptyList());
        pedido.setDataCriacao(dataCriacao);
        pedido.setTotal(total);
        pedido.setStatus(status);
        
        PedidoDTO dto = mapper.toDTO(pedido);
        
        assertNotNull(dto, "O DTO convertido não deve ser nulo");
        assertEquals(pedido.getId(), dto.getId());
        assertEquals(pedido.getClienteId(), dto.getClienteId());
        assertEquals(pedido.getItens().size(), dto.getItens().size());
        assertEquals(pedido.getDataCriacao(), dto.getDataCriacao());
        assertEquals(pedido.getTotal(), dto.getTotal());
        // Verifica se a conversão do status (Enum -> String) ocorreu corretamente
        assertEquals(status.name(), dto.getStatus());
    }
}