package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RotaDTO {
    private UUID id;
    private UUID entregaId;
    private String pontoPartida;
    private String destino;
    private double distancia;
    private double tempoEstimado;
}
