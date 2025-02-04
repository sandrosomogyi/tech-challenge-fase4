package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers.ItemPedidoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers.PedidoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.ItemPedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ItemPedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.PedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.ClienteService;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.ProdutoService;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CriarPedidoUseCase {

    private final PedidoJpaRepository pedidoJpaRepository;
    private  final ItemPedidoJpaRepository itemPedidoJpaRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final RestTemplate restTemplate;

    public CriarPedidoUseCase(PedidoJpaRepository pedidoJpaRepository,
                              ItemPedidoJpaRepository itemPedidoJpaRepository,
                              ClienteService clienteService,
                              ProdutoService produtoService,
                              RestTemplate restTemplate) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.itemPedidoJpaRepository = itemPedidoJpaRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.restTemplate = restTemplate;
    }

    public Pedido executar(PedidoDTO pedido) {

        // Validar que o pedido possui itens
        if (pedido.getItens().isEmpty()) {
            throw new ControllerMessagingException("O pedido deve conter pelo menos um item.");
        }

        // Verificar cliente
        ClienteDTO cliente = clienteService.getClienteById(pedido.getClienteId());
        if (cliente == null) {
            throw new ControllerNotFoundException("Cliente não encontrado.");
        }

        // Definir o status inicial do pedido
        pedido.setStatus(String.valueOf(PedidoStatus.PENDENTE));

        List<ItemPedidoDTO> itensDTO = pedido.getItens();
        pedido.setItens(new ArrayList<>());

        pedido.setDataCriacao(LocalDateTime.now());

        // Salva o pedido no banco de dados para receber um id e tratar itens
        Pedido pedidoSalvo = pedidoJpaRepository.save(PedidoMapper.toEntity(pedido));

        // Verificar e atualizar produtos
        itensDTO.forEach(item -> {


            ProdutoDTO produto = produtoService.getProdutoById(item.getProdutoId());
            if (produto == null) {
                pedidoJpaRepository.delete(pedidoSalvo);
                throw new ControllerNotFoundException("Produto não encontrado: " + item.getProdutoId());
            }

            // Verificar estoque
            if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
                pedidoJpaRepository.delete(pedidoSalvo);
                throw new ControllerMessagingException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualizar estoque do produto
            produtoService.updateEstoqueProduto(produto, item.getQuantidade());



            item.setTotal(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));

            var entityItem = ItemPedidoMapper.toEntity(item);

            entityItem.setPedido(pedidoSalvo);

            var itemSalvo = itemPedidoJpaRepository.save(entityItem);

            pedidoSalvo.getItens().add(itemSalvo);
        });

        // Calcular o valor total do pedido
        BigDecimal total = pedidoSalvo.getItens().stream()
                .map(ItemPedido::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedidoSalvo.setTotal(total);

        // Salva o pedido completo no banco de dados
        Pedido pedidoFinal = pedidoJpaRepository.save(pedidoSalvo);

        // Atualizar o estoque do produto (já feito para cada item individualmente)
        return pedidoFinal;
    }
}
