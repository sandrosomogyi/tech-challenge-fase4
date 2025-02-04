package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public static Produto toEntity(ProdutoDTO dto) {
        return new Produto(
                dto.getId(),
                dto.getNome(),
                dto.getDescricao(),
                dto.getPreco(),
                dto.getQuantidadeEstoque()
        );
    }

    public static ProdutoDTO toDTO(Produto entity) {
        return new ProdutoDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getQuantidadeEstoque()
        );
    }
}
