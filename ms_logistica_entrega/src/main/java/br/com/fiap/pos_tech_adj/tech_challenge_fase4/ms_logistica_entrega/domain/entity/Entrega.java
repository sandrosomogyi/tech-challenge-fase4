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
    private StatusEntrega status;
    private String enderecoDestino;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraConclusao;
    private String codigoRastreamento;

    public Entrega(UUID pedidoId, UUID entregadorId, String enderecoDestino, LocalDateTime dataHoraPrevista) {
        this.id = UUID.randomUUID();
        this.pedidoId = pedidoId;
        this.entregadorId = entregadorId;
        this.status = StatusEntrega.PENDENTE;
        this.enderecoDestino = enderecoDestino;
        this.dataHoraPrevista = dataHoraPrevista;
        this.codigoRastreamento = UUID.randomUUID().toString();
    }

    public void atualizarStatus(StatusEntrega novoStatus) {
        this.status = novoStatus;
        if (novoStatus == StatusEntrega.ENTREGUE) {
            this.dataHoraConclusao = LocalDateTime.now();
        }
    }
}
