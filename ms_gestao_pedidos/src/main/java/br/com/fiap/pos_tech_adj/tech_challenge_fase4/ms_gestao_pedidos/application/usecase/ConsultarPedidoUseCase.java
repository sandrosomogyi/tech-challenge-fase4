package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerMessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultarPedidoUseCase {

    private final PedidoJpaRepository pedidoJpaRepository;

    public ConsultarPedidoUseCase(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    public Pedido executar(UUID id) {
        // Busca o pedido no banco de dados por ID
        Pedido pedido = pedidoJpaRepository.findById(id)
                .orElseThrow(() -> new ControllerMessagingException("Pedido não encontrado"));

        // Publicar evento no Kafka

        // Lógica de tratamento se o pedido não for encontrado (por exemplo, lançar uma exceção)
        return pedido;
    }

    public List<Pedido> executarTodos() {
        // Recupera todos os pedidos
        return pedidoJpaRepository.findAll();
    }
}
