package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ClienteDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Service
public class ClienteService {

    private final RestTemplate restTemplate;
    private final String clienteServiceUrl = "http://localhost:8082/api/clientes"; // URL do microserviço de clientes

    public ClienteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDTO getClienteById(UUID clienteId) {
        // Fazendo a requisição para o microserviço de clientes
        return restTemplate.getForObject(clienteServiceUrl + "/{id}", ClienteDTO.class, clienteId);
    }
}
