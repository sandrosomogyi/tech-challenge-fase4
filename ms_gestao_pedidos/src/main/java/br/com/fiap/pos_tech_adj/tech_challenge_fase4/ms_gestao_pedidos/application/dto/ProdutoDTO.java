package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;

    @Override
    public String toString() {
        return "ProdutoDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
                + ", quantidadeEstoque=" + quantidadeEstoque + ", getId()=" + getId() + ", getNome()=" + getNome()
                + ", getDescricao()=" + getDescricao() + ", getPreco()=" + getPreco() + ", getQuantidadeEstoque()="
                + getQuantidadeEstoque() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
}
