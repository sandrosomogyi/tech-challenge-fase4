package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;


import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class PedidoService {

    private final RestTemplate restTemplate;

    public PedidoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public boolean atualizarStatusPedido(String pedidoId, String status) {
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl("http://localhost:8080/api/pedidos/atualizar-status")
                    .queryParam("pedidoId", pedidoId)
                    .queryParam("status", status)
                    .build()
                    .toUriString();

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    null,
                    String.class
            );

            if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
