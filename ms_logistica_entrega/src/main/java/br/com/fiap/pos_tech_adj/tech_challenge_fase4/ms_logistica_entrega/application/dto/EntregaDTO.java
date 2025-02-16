package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class EntregaDTO {
    private UUID id;
    private UUID pedidoId;
    private UUID entregadorId;
    private String enderecoDestino;
    private StatusEntrega status;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraConclusao;
    private String codigoRastreio;

    public EntregaDTO() {}

    public EntregaDTO(UUID id, UUID pedidoId, UUID entregadorId, String enderecoDestino, StatusEntrega status, LocalDateTime dataHoraPrevista, LocalDateTime dataHoraConclusao, String codigoRastreio) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.entregadorId = entregadorId;
        this.enderecoDestino = enderecoDestino;
        this.status = status;
        this.dataHoraPrevista = dataHoraPrevista;
        this.dataHoraConclusao = dataHoraConclusao;
        this.codigoRastreio = codigoRastreio;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntregaDTO that = (EntregaDTO) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(status, that.status);
               // Comparar outros campos...
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
        // Incluir outros campos...
    }
}
