package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_rota")
public class Rota {
    @Id @GeneratedValue
    private UUID id;
    @Column(name = "entrega_id")
    private UUID entregaId;
    private String pontoPartida;
    private String destino;
    private double distancia;
    private double tempoEstimado;
    private LocalDateTime dataSaida;

    public Rota() {
    }

    public Rota(UUID id, UUID entregaId, String pontoPartida, String destino, double distancia, double tempoEstimado, LocalDateTime dataSaida) {
        this.id = id;
        this.entregaId = entregaId;
        this.pontoPartida = pontoPartida;
        this.destino = destino;
        this.distancia = distancia;
        this.tempoEstimado = tempoEstimado;
        this.dataSaida = dataSaida;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(UUID entregaId) {
        this.entregaId = entregaId;
    }

    public String getPontoPartida() {
        return pontoPartida;
    }

    public void setPontoPartida(String pontoPartida) {
        this.pontoPartida = pontoPartida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rota rota)) return false;
        return Double.compare(distancia, rota.distancia) == 0 && Double.compare(tempoEstimado, rota.tempoEstimado) == 0 && Objects.equals(id, rota.id) && Objects.equals(entregaId, rota.entregaId) && Objects.equals(pontoPartida, rota.pontoPartida) && Objects.equals(destino, rota.destino) && Objects.equals(dataSaida, rota.dataSaida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entregaId, pontoPartida, destino, distancia, tempoEstimado, dataSaida);
    }

    @Override
    public String toString() {
        return "Rota{" +
                "id=" + id +
                ", entregaId=" + entregaId +
                ", pontoPartida='" + pontoPartida + '\'' +
                ", destino='" + destino + '\'' +
                ", distancia=" + distancia +
                ", tempoEstimado=" + tempoEstimado +
                ", dataSaida=" + dataSaida +
                '}';
    }
}
