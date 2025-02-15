package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.event.PedidoCriadoEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PedidoEventPublisher {

    private final StreamBridge streamBridge;

    public PedidoEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publicarPedidoCriado(UUID pedidoId, String endereco) {
        PedidoCriadoEvent event = new PedidoCriadoEvent(pedidoId, endereco);
        streamBridge.send("pedidoCriado-out-0", event);
    }
}
