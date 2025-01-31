package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_itemPedido")
@Data @NoArgsConstructor @AllArgsConstructor
public class ItemPedido {
    private UUID id;
    private Produto produto;
    private UUID pedidoId;
    private int quantidade;

    public BigDecimal getTotal() {
        return produto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));
    }
}