package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.PedidoJpaRepository;
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

class PedidoRepositoryImplTest {

    @Mock
    private PedidoJpaRepository repository;

    @InjectMocks
    private PedidoRepositoryImpl pedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Pedido pedido = new Pedido();
        when(repository.save(pedido)).thenReturn(pedido);

        Pedido result = pedidoRepository.save(pedido);

        assertNotNull(result);
        assertEquals(pedido, result);
        verify(repository, times(1)).save(pedido);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Pedido pedido = new Pedido();
        when(repository.findById(id)).thenReturn(Optional.of(pedido));

        Optional<Pedido> result = pedidoRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(pedido, result.get());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindAll() {
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);
        when(repository.findAll()).thenReturn(pedidos);

        List<Pedido> result = pedidoRepository.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(pedidos, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        doNothing().when(repository).deleteById(id);

        pedidoRepository.delete(id);

        verify(repository, times(1)).deleteById(id);
    }
}