package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double precoUnitario;
}
