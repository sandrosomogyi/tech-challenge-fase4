package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tb_cliente")
public class Cliente {

    @Id @GeneratedValue
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String email;
    private String telefone;

    private String endereco;
}