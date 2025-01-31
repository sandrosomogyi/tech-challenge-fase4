package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Produto;


public class ProdutoMapper {

    public static Produto toEntity(ProdutoDTO dto) {
        return new Produto(
                dto.getId(),
                dto.getNome(),
                dto.getPrecoUnitario()
        );
    }

    public static ProdutoDTO toDTO(Produto entity) {
        return new ProdutoDTO(
                entity.getId(),
                entity.getNome(),
                entity.getPrecoUnitario()
        );
    }
}
