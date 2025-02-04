package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregadorDTO {
    private UUID id;
    private String nome;
    private String telefone;
    private String veiculo;
}
