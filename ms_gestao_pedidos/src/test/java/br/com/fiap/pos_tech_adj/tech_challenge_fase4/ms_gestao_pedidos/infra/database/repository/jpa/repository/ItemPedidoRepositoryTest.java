package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.repository.ItemPedidoRepository;

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

class ItemPedidoRepositoryTest {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @InjectMocks
    private ItemPedidoRepositoryTest itemPedidoRepositoryTest;

    private ItemPedido itemPedido;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        itemPedido = new ItemPedido();
        itemPedido.setId(id);
    }

    @Test
    void testSave() {
        when(itemPedidoRepository.save(itemPedido)).thenReturn(itemPedido);
        ItemPedido savedItemPedido = itemPedidoRepository.save(itemPedido);
        assertNotNull(savedItemPedido);
        assertEquals(id, savedItemPedido.getId());
        verify(itemPedidoRepository, times(1)).save(itemPedido);
    }

    @Test
    void testFindAll() {
        List<ItemPedido> itemPedidos = Arrays.asList(itemPedido);
        when(itemPedidoRepository.findAll()).thenReturn(itemPedidos);
        List<ItemPedido> result = itemPedidoRepository.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(itemPedidoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(itemPedidoRepository.findById(id)).thenReturn(Optional.of(itemPedido));
        Optional<ItemPedido> result = itemPedidoRepository.findById(id);
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(itemPedidoRepository, times(1)).findById(id);
    }

    @Test
    void testDelete() {
        doNothing().when(itemPedidoRepository).delete(id);
        itemPedidoRepository.delete(id);
        verify(itemPedidoRepository, times(1)).delete(id);
    }
}