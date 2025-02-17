package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.PedidoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PedidoRepositoryTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoRepositoryTest pedidoRepositoryTest;

    private Pedido pedido;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        pedido = new Pedido();
        pedido.setId(id);
    }

    @Test
    void testSave() {
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido savedPedido = pedidoRepository.save(pedido);

        assertNotNull(savedPedido);
        assertEquals(pedido.getId(), savedPedido.getId());
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void testFindAll() {
        List<Pedido> pedidos = Arrays.asList(pedido);
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> result = pedidoRepository.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        Optional<Pedido> result = pedidoRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(pedido.getId(), result.get().getId());
        verify(pedidoRepository, times(1)).findById(id);
    }

    @Test
    void testDelete() {
        doNothing().when(pedidoRepository).delete(id);

        pedidoRepository.delete(id);

        verify(pedidoRepository, times(1)).delete(id);
    }
}