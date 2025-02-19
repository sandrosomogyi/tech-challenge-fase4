package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
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
    
    public Pedido(UUID uuid, UUID uuid2, List<ItemPedido> list, LocalDateTime localDateTime, BigDecimal bigDecimal, PedidoStatus pedidoStatus) { 
        
     }

    public Pedido(UUID clienteId, List<ItemPedido> itens) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.itens = itens;
        this.dataCriacao = LocalDateTime.now();
        this.status = PedidoStatus.PENDENTE;
    }


    public Pedido() {
        
    }

    public Pedido(UUID pedidoId, String string) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

   

    public void concluirPedido() {
        this.status = PedidoStatus.CONCLUIDO;
    }

    public void cancelarPedido() {
        this.status = PedidoStatus.CANCELADO;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((clienteId == null) ? 0 : clienteId.hashCode());
        result = prime * result + ((itens == null) ? 0 : itens.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }


    
}