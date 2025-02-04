package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "cliente_id")
    private UUID clienteId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference  // Indica que esta é a "referência principal"
    private List<ItemPedido> itens;

    private LocalDateTime dataCriacao;
    private BigDecimal total;
    private PedidoStatus status;

    public Pedido(UUID clienteId, List<ItemPedido> itens) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
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