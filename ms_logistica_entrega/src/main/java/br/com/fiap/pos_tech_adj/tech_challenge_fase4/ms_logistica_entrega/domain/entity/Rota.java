package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
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
}
