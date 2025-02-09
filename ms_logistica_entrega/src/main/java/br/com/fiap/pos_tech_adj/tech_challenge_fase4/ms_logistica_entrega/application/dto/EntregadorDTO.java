package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

public class EntregadorDTO {
    private UUID id;
    private String nome;
    private String telefone;
    private String veiculo;

    public EntregadorDTO(){}

    public EntregadorDTO(UUID id, String nome, String telefone, String veiculo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.veiculo = veiculo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
}
