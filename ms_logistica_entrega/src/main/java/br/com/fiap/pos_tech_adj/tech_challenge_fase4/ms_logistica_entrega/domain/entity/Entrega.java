package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_entrega")
public class Entrega {
    @Id @GeneratedValue
    private UUID id;
    @Column(name = "pedido_id", unique = true)
    private UUID pedidoId;
    @Column(name = "entregador_id")
    private UUID entregadorId;
    private String enderecoDestino;
    private StatusEntrega status;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraConclusao;
    @Column(unique = true)
    private String codigoRastreio;

    public Entrega(){}

    public Entrega(UUID id, UUID pedidoId, UUID entregadorId, String enderecoDestino, StatusEntrega status, LocalDateTime dataHoraPrevista, LocalDateTime dataHoraConclusao, String codigoRastreio) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.entregadorId = entregadorId;
        this.enderecoDestino = enderecoDestino;
        this.status = status;
        this.dataHoraPrevista = dataHoraPrevista;
        this.dataHoraConclusao = dataHoraConclusao;
        this.codigoRastreio = codigoRastreio;
    }

    public void atualizarStatus(StatusEntrega novoStatus) {
        this.status = novoStatus;
        if (novoStatus == StatusEntrega.CONCLUIDA) {
            this.dataHoraConclusao = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public UUID getEntregadorId() {
        return entregadorId;
    }

    public void setEntregadorId(UUID entregadorId) {
        this.entregadorId = entregadorId;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraPrevista() {
        return dataHoraPrevista;
    }

    public void setDataHoraPrevista(LocalDateTime dataHoraPrevista) {
        this.dataHoraPrevista = dataHoraPrevista;
    }

    public LocalDateTime getDataHoraConclusao() {
        return dataHoraConclusao;
    }

    public void setDataHoraConclusao(LocalDateTime dataHoraConclusao) {
        this.dataHoraConclusao = dataHoraConclusao;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entrega entrega)) return false;
        return Objects.equals(id, entrega.id) && Objects.equals(pedidoId, entrega.pedidoId) && Objects.equals(entregadorId, entrega.entregadorId) && Objects.equals(enderecoDestino, entrega.enderecoDestino) && status == entrega.status && Objects.equals(dataHoraPrevista, entrega.dataHoraPrevista) && Objects.equals(dataHoraConclusao, entrega.dataHoraConclusao) && Objects.equals(codigoRastreio, entrega.codigoRastreio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedidoId, entregadorId, enderecoDestino, status, dataHoraPrevista, dataHoraConclusao, codigoRastreio);
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", entregadorId=" + entregadorId +
                ", enderecoDestino='" + enderecoDestino + '\'' +
                ", status=" + status +
                ", dataHoraPrevista=" + dataHoraPrevista +
                ", dataHoraConclusao=" + dataHoraConclusao +
                ", codigoRastreio='" + codigoRastreio + '\'' +
                '}';
    }
}
