package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class PedidoDTO {
    private UUID id;
    private UUID clienteId;
    private List<ItemPedidoDTO> itens;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCriacao;
    private BigDecimal total;
    private String status;

    public PedidoDTO() {
    }

    public PedidoDTO(UUID id, UUID clienteId, List<ItemPedidoDTO> itens, LocalDateTime dataCriacao, BigDecimal total, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.dataCriacao = dataCriacao;
        this.total = total;
        this.status = status;
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
    public List<ItemPedidoDTO> getItens() {
        return itens;
    }
    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public PedidoDTO id(UUID id) {
        this.id = id;
        return this;
    }
    public PedidoDTO clienteId(UUID clienteId) {
        this.clienteId = clienteId;
        return this;
    }
    public PedidoDTO itens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
        return this;
    }
    public PedidoDTO dataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }
    public PedidoDTO total(BigDecimal total) {
        this.total = total;
        return this;
    }
    public PedidoDTO status(String status) {
        this.status = status;
        return this;
    }
    @Override
    public String toString() {
        return "PedidoDTO{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", itens=" + itens +
                ", dataCriacao=" + dataCriacao +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }


}
