package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EntregaDTO {
    private UUID id;
    private UUID pedidoId;
    private UUID entregadorId;
    private String enderecoDestino;
    private String status;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraConclusao;
    private String codigoRastreamento;

    public EntregaDTO() {
    }

    public EntregaDTO(UUID id, UUID pedidoId, UUID entregadorId, String enderecoDestino, String status,
            LocalDateTime dataHoraPrevista, LocalDateTime dataHoraConclusao, String codigoRastreamento) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.entregadorId = entregadorId;
        this.enderecoDestino = enderecoDestino;
        this.status = status;
        this.dataHoraPrevista = dataHoraPrevista;
        this.dataHoraConclusao = dataHoraConclusao;
        this.codigoRastreamento = codigoRastreamento;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getCodigoRastreamento() {
        return codigoRastreamento;
    }

    public void setCodigoRastreamento(String codigoRastreamento) {
        this.codigoRastreamento = codigoRastreamento;
    }

    @Override
    public String toString() {
        return "EntregaDTO [id=" + id + ", pedidoId=" + pedidoId + ", entregadorId=" + entregadorId
                + ", enderecoDestino=" + enderecoDestino + ", status=" + status + ", dataHoraPrevista="
                + dataHoraPrevista + ", dataHoraConclusao=" + dataHoraConclusao + ", codigoRastreamento="
                + codigoRastreamento + "]";
    }
}
