package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_itemPedido")
@Data @NoArgsConstructor @AllArgsConstructor
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
}