package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteDTO dto) {
        return new Cliente(
                dto.getId(),
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone()
        );
    }

    public static ClienteDTO toDTO(Cliente entity) {
        return new ClienteDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone()
        );
    }
}
