package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public ConsultarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido executar(UUID id) {
        // Busca o pedido no banco de dados por ID
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        // Lógica de tratamento se o pedido não for encontrado (por exemplo, lançar uma exceção)
        return pedido.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<Pedido> executarTodos() {
        // Recupera todos os pedidos
        return pedidoRepository.findAll();
    }
}
