package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto;

import java.math.BigDecimal;
import java.util.UUID;


public class ItemPedidoDTO {
    private UUID id;
    private Long produtoId;

    private int quantidade;
    private BigDecimal total;

    public ItemPedidoDTO() {
    }
    
    public ItemPedidoDTO(UUID id, Long produtoId, int quantidade, BigDecimal total) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    
}
