package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ProdutoDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Service
public class ProdutoService {

    private final RestTemplate restTemplate;
    private final String produtoServiceUrl = "http://localhost:8081/api/produtos"; // URL do microserviço de produtos

    public ProdutoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProdutoDTO getProdutoById(Long produtoId) {
        // Fazendo a requisição para o microserviço de produtos
        return restTemplate.getForObject(produtoServiceUrl + "/{id}", ProdutoDTO.class, produtoId);
    }

    public void updateEstoqueProduto(ProdutoDTO produto, int quantidadeVendida) {
        // Atualiza o estoque do produto no microserviço de produtos
        String url = "http://localhost:8081/api/produtos/subtrair-estoque/" + produto.getId()
                + "?quantidade=" + quantidadeVendida; // URL do microserviço de produtos

        // Fazendo a requisição PATCH para atualizar o estoque
        restTemplate.exchange(url, HttpMethod.PUT, null, Void.class);
    }
}
