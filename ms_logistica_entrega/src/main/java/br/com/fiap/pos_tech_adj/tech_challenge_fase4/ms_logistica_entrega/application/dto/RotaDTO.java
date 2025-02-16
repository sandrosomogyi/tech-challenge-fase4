package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class RotaDTO {
    private UUID id;
    private UUID entregaId;
    private String pontoPartida;
    private String destino;
    private double distancia;
    private double tempoEstimado;
    private LocalDateTime dataSaida;

    public RotaDTO(){}

    public RotaDTO(UUID id, UUID entregaId, String pontoPartida, String destino, double distancia, double tempoEstimado, LocalDateTime dataSaida) {
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
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RotaDTO rotaDTO = (RotaDTO) o;
    return Objects.equals(id, rotaDTO.id) &&
           Objects.equals(pontoPartida, rotaDTO.pontoPartida) &&
           Objects.equals(destino, rotaDTO.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pontoPartida, destino);
    }

}
