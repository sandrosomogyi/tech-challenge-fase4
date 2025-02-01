package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
    private LocalDateTime dataCriacao;
    private BigDecimal total;
    private PedidoStatus status;

    public Pedido(Cliente cliente, List<ItemPedido> itens) {
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.itens = itens;
        this.dataCriacao = LocalDateTime.now();
        this.status = PedidoStatus.PENDENTE;
    }

    public void concluirPedido() {
        this.status = PedidoStatus.CONCLUIDO;
    }

    public void cancelarPedido() {
        this.status = PedidoStatus.CANCELADO;
    }
}