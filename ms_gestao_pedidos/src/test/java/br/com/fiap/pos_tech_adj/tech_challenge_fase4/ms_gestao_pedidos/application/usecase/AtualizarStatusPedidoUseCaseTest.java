package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service.PedidoEventPublisher;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions.ControllerNotFoundException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarStatusPedidoUseCaseTest {

    @Mock
    private PedidoJpaRepository pedidoJpaRepository;
    
    @Mock
    private PedidoEventPublisher eventPublisher;
    
    @InjectMocks
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    private UUID pedidoId;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedidoId = UUID.randomUUID();
        pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(PedidoStatus.PENDENTE);
    }

    @Test
    void deveAtualizarStatusPedidoComSucesso() {
        when(pedidoJpaRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));
        when(pedidoJpaRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido pedidoAtualizado = atualizarStatusPedidoUseCase.executar(pedidoId, "EM_TRANSITO");

        assertEquals(PedidoStatus.EM_TRANSITO, pedidoAtualizado.getStatus());
        verify(pedidoJpaRepository).save(pedido);
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoEncontrado() {
        when(pedidoJpaRepository.findById(pedidoId)).thenReturn(Optional.empty());

        assertThrows(ControllerNotFoundException.class, () -> atualizarStatusPedidoUseCase.executar(pedidoId, "EM_TRANSITO"));
    }

    @Test
    void deveLancarExcecaoQuandoPedidoCancelado() {
        pedido.setStatus(PedidoStatus.CANCELADO);
        when(pedidoJpaRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        assertThrows(ControllerMessagingException.class, () -> atualizarStatusPedidoUseCase.executar(pedidoId, "EM_TRANSITO"));
    }

    @Test
    void deveLancarExcecaoQuandoTentativaDeMudarDePendenteParaConcluido() {
        when(pedidoJpaRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        assertThrows(ControllerMessagingException.class, () -> atualizarStatusPedidoUseCase.executar(pedidoId, "CONCLUIDO"));
    }

    @Test
    void deveLancarExcecaoQuandoTentativaDeMudarDeEmTransitoParaPendente() {
        pedido.setStatus(PedidoStatus.EM_TRANSITO);
        when(pedidoJpaRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        assertThrows(ControllerMessagingException.class, () -> atualizarStatusPedidoUseCase.executar(pedidoId, "PENDENTE"));
    }

    @Test
    void deveLancarExcecaoQuandoTentativaDeMudarPedidoConcluido() {
        pedido.setStatus(PedidoStatus.CONCLUIDO);
        when(pedidoJpaRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        assertThrows(ControllerMessagingException.class, () -> atualizarStatusPedidoUseCase.executar(pedidoId, "PENDENTE"));
    }
}