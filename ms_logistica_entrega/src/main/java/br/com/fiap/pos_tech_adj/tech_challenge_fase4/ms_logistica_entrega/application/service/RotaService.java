package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

@Service
public class RotaService {

    private static final String OSRM_API_URL = "http://router.project-osrm.org/route/v1/driving/";

    private final RestTemplate restTemplate;

    public RotaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Rota calcularMelhorRota(UUID entregaId, String origem, String destino) {

        //Converte para latitude e longitude
        String origemConvertido = convertEndereco(origem);
        String destinoConvertido = convertEndereco(destino);

        // Substitua "origem" e "destino" por coordenadas no formato "longitude,latitude"
        String url = UriComponentsBuilder.fromHttpUrl(OSRM_API_URL)
                .pathSegment(origemConvertido + ";" + destinoConvertido)
                .queryParam("overview", "false")
                .toUriString();

        // Faz a requisição HTTP para a API de roteamento OSRM
        String response = restTemplate.getForObject(url, String.class);

        JSONObject jsonObject = new JSONObject(response);

        // Acessando o array de rotas
        JSONArray routes = jsonObject.getJSONArray("routes");

        // Se você precisar acessar as propriedades dentro da rota
        JSONObject firstRoute = routes.getJSONObject(0);

        // Verifique se a rota não está vazia
        if (firstRoute != null) {
            // Acessando diretamente as propriedades de "distance" e "duration"
            Double distanciaKM = firstRoute.getDouble("distance") / 1000; // Convertendo metros para quilômetros
            Double tempoMinutos = firstRoute.getDouble("duration") / 60; // Convertendo segundos para minutos

            // Cria o objeto Rota com as informações obtidas
            Rota rota = new Rota(
                    null,
                    entregaId,
                    origem,
                    destino,
                    distanciaKM,
                    tempoMinutos
            );

            // Retorna a rota calculada
            return rota;
        }

        return null; // Caso não seja possível calcular a rota
    }

    private String convertEndereco(String endereco)  {
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl("https://nominatim.openstreetmap.org/search")
                    .queryParam("format", "json")
                    .queryParam("q", endereco + ",BR") // Não codifica manualmente
                    .build()
                    .toUriString();


            String response = restTemplate.getForObject(url, String.class);
            JSONArray jsonArray = new JSONArray(response);

            if (!jsonArray.isEmpty()) {
                JSONObject location = jsonArray.getJSONObject(0);
                String lat = location.getString("lat");
                String lon = location.getString("lon");

                return lon + "," + lat;  // Já no formato exigido pelo OSRM
            }else {
                throw new ControllerMessagingException("Não foi possivel realizar a conversão do endereço: " + endereco);
            }
        } catch (Exception e) {
            throw new ControllerMessagingException(e.getMessage());
        }
    }
}
