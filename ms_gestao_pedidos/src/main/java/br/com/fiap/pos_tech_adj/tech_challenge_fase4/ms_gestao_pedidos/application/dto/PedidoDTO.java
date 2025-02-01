package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
public class PedidoDTO {
    private UUID id;
    private ClienteDTO cliente;
    private List<ItemPedidoDTO> itens;
    private LocalDateTime dataCriacao;
    private BigDecimal total;
    private String status;
}
