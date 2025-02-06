package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
}
