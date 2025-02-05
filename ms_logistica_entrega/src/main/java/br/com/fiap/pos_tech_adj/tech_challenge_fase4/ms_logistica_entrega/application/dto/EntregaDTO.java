package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregaDTO {
    private UUID id;
    private UUID pedidoId;
    private UUID entregadorId;
    private String enderecoDestino;
    private StatusEntrega status;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraConclusao;
    private String codigoRastreamento;
}
