package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultarPedidoUseCaseTest {

    private PedidoJpaRepository pedidoJpaRepository;
    private ConsultarPedidoUseCase consultarPedidoUseCase;

    @BeforeEach
    public void setUp() {
        pedidoJpaRepository = mock(PedidoJpaRepository.class);
        consultarPedidoUseCase = new ConsultarPedidoUseCase(pedidoJpaRepository);
    }

    @Test
    public void testExecutarComPedidoNaoEncontrado() {
        UUID pedidoId = UUID.randomUUID();

        when(pedidoJpaRepository.findById(pedidoId)).thenReturn(Optional.empty());

        ControllerMessagingException exception = assertThrows(ControllerMessagingException.class, () -> {
            consultarPedidoUseCase.executar(pedidoId);
        });

        assertEquals("Pedido não encontrado", exception.getMessage());
    }

}