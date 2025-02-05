package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "tb_entregador")
public class Entregador {
    @Id @GeneratedValue
    private UUID id;
    private String nome;
    private String telefone;
    private String veiculo;
}
