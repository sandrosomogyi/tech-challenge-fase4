package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.event.EntregaAtualizadaEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EntregaEventPublisher {

    private final StreamBridge streamBridge;

    public EntregaEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publicarEntregaAtualizada(UUID pedidoId, String statusEntrega) {
        EntregaAtualizadaEvent event = new EntregaAtualizadaEvent(pedidoId, statusEntrega);
        streamBridge.send("entregaAtualizada-out-0", event);
    }
}
