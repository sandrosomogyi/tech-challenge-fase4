package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;


@Entity
@Table(name = "tb_itemPedido")

public class ItemPedido {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "produto_id")
    private Long produtoId;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    @JsonBackReference  // Impede recursão infinita na serialização
    private Pedido pedido;

    private int quantidade;
    private BigDecimal total;

    public ItemPedido(UUID id, Long produtoId, Pedido pedido, int quantidade, BigDecimal total) {
        this.id = id;
        this.produtoId = produtoId;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.total = total;
    }

    public ItemPedido() {
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
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    @Override
    public String toString() {
        return "ItemPedido [id=" + id + ", produtoId=" + produtoId + ", pedido=" + pedido + ", quantidade=" + quantidade
                + ", total=" + total + ", getId()=" + getId() + ", getProdutoId()=" + getProdutoId() + ", getPedido()="
                + getPedido() + ", getQuantidade()=" + getQuantidade() + ", getTotal()=" + getTotal() + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
}