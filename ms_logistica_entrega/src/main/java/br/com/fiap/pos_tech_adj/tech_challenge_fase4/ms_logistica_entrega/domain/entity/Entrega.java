package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "tb_entrega")
public class Entrega {
    @Id @GeneratedValue
    private UUID id;
    @Column(name = "pedido_id")
    private UUID pedidoId;
    @Column(name = "entregador_id")
    private UUID entregadorId;
    private String enderecoDestino;
    private StatusEntrega status;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraConclusao;
    private String codigoRastreamento;

    public void atualizarStatus(StatusEntrega novoStatus) {
        this.status = novoStatus;
        if (novoStatus == StatusEntrega.ENTREGUE) {
            this.dataHoraConclusao = LocalDateTime.now();
        }
    }
}
