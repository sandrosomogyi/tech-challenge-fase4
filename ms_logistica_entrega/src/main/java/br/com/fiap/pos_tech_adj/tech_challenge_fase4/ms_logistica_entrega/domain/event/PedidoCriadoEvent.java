package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.event;

import java.util.UUID;

public record PedidoCriadoEvent(UUID pedidoId, String endereco) { }
