package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.EntregaDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EntregaService {
    private final RestTemplate restTemplate;
    private final String entregaServiceUrl = "http://localhost:8083/api/entregas"; // URL do microserviço de entregas

    public EntregaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EntregaDTO criarEntrega(EntregaDTO entrega) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<EntregaDTO> requestEntity = new HttpEntity<>(entrega, headers);
            return restTemplate.postForObject(entregaServiceUrl, requestEntity, EntregaDTO.class);

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Erro ao comunicar com o serviço de entregas: " + e.getMessage(), e);
        }
    }
}
