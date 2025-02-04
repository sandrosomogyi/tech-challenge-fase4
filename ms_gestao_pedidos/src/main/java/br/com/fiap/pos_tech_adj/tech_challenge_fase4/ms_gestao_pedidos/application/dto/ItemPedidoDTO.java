package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
public class ItemPedidoDTO {
    private UUID id;
    private Long produtoId;

    private int quantidade;
    private BigDecimal total;
}
