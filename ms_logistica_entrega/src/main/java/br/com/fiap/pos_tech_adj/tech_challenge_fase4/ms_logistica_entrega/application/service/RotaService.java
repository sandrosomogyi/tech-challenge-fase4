package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
public class RotaService {

    private static final String OSRM_API_URL = "http://router.project-osrm.org/route/v1/driving/";

    private final RestTemplate restTemplate;

    public RotaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Rota calcularMelhorRota(UUID entregaId, String origem, String destino) {
        // Substitua "origem" e "destino" por coordenadas no formato "longitude,latitude"
        String url = UriComponentsBuilder.fromHttpUrl(OSRM_API_URL)
                .pathSegment(origem, destino)
                .queryParam("overview", "false")
                .toUriString();

        // Faz a requisição HTTP para a API de roteamento OSRM
        String response = restTemplate.getForObject(url, String.class);

        if (response != null && response.contains("routes")) {
            // Extrai a distância e o tempo estimado da resposta JSON
            double distancia = parseDistancia(response);
            double tempoEstimado = parseTempoEstimado(response);

            // Cria o objeto Rota com as informações obtidas
            Rota rota = new Rota(
                    UUID.randomUUID(),
                    entregaId,
                    origem,
                    destino,
                    distancia,
                    tempoEstimado
            );

            // Retorna a rota calculada
            return rota;
        }

        return null; // Caso não seja possível calcular a rota
    }

    // Método para extrair a distância da resposta JSON
    private double parseDistancia(String response) {
        // O JSON de resposta tem a seguinte estrutura:
        // "routes" : [{"legs": [{"distance": {"value": 10000}}]}]
        String distanceValueStr = response.split("\"distance\" : \\{")[1].split("\"value\" : ")[1].split(",")[0];
        return Double.parseDouble(distanceValueStr) / 1000; // Convertendo metros para quilômetros
    }

    // Método para extrair o tempo estimado da resposta JSON
    private double parseTempoEstimado(String response) {
        // O JSON de resposta tem a seguinte estrutura:
        // "routes" : [{"legs": [{"duration": {"value": 600}}]}]
        String durationValueStr = response.split("\"duration\" : \\{")[1].split("\"value\" : ")[1].split(",")[0];
        return Double.parseDouble(durationValueStr) / 60; // Convertendo segundos para minutos
    }
}
