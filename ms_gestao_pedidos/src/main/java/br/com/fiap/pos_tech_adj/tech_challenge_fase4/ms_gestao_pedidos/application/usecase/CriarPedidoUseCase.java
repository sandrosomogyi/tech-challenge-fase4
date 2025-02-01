package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers.ProdutoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.ClienteService;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.ProdutoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CriarPedidoUseCase {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final RestTemplate restTemplate;

    public CriarPedidoUseCase(PedidoJpaRepository pedidoJpaRepository, ClienteService clienteService, ProdutoService produtoService, RestTemplate restTemplate) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.restTemplate = restTemplate;
    }

    public Pedido executar(Pedido pedido) {

        // Validar que o pedido possui itens
        if (pedido.getItens().isEmpty()) {
            throw new RuntimeException("O pedido deve conter pelo menos um item.");
        }

        // Verificar cliente
        ClienteDTO cliente = clienteService.getClienteById(pedido.getCliente().getId());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        // Verificar e atualizar produtos
        pedido.getItens().forEach(item -> {
            ProdutoDTO produto = produtoService.getProdutoById(item.getProduto().getId());
            if (produto == null) {
                throw new RuntimeException("Produto não encontrado: " + item.getProduto().getId());
            }
            // Atualizar o produto no item com os dados do microserviço
            item.setProduto(ProdutoMapper.toEntity(produto));

            // Verificar estoque
            if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualizar estoque do produto
            updateEstoqueProduto(produto, item.getQuantidade());
        });

        // Calcular o valor total do pedido
        BigDecimal total = pedido.getItens().stream()
                .map(item -> item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setTotal(total);

        // Definir o status inicial do pedido
        pedido.setStatus(PedidoStatus.PENDENTE);

        // Salva o pedido no banco de dados
        Pedido pedidoSalvo = pedidoJpaRepository.save(pedido);

        // Atualizar o estoque do produto (já feito para cada item individualmente)
        return pedidoSalvo;
    }

    private void updateEstoqueProduto(ProdutoDTO produto, int quantidadeVendida) {
        // Atualiza o estoque do produto no microserviço de produtos
        String url = "http://localhost:8081/produtos/" + produto.getId() + "/estoque"; // URL do microserviço de produtos

        // Preparando o corpo da requisição
        ProdutoDTO produtoAtualizado = new ProdutoDTO(produto.getId(), produto.getNome(),
                produto.getDescricao(), produto.getPreco(),
                produto.getQuantidadeEstoque() - quantidadeVendida); // Atualizando a quantidade em estoque

        // Fazendo a requisição PATCH para atualizar o estoque
        restTemplate.exchange(url, HttpMethod.PATCH, null, Void.class);
    }
}
