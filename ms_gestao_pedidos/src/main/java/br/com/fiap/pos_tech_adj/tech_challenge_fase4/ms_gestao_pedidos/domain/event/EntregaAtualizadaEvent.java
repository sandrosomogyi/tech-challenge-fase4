package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.event;

import java.util.UUID;

public record EntregaAtualizadaEvent(UUID pedidoId, String statusEntrega) {
}
