package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.infra.database.repository.jpa.ItemPedidoJpaRepository;
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

class ItemPedidoRepositoryImplTest {

    @Mock
    private ItemPedidoJpaRepository repository;

    @InjectMocks
    private ItemPedidoRepositoryImpl itemPedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        ItemPedido itemPedido = new ItemPedido();
        when(repository.save(itemPedido)).thenReturn(itemPedido);

        ItemPedido result = itemPedidoRepository.save(itemPedido);

        assertNotNull(result);
        assertEquals(itemPedido, result);
        verify(repository, times(1)).save(itemPedido);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        ItemPedido itemPedido = new ItemPedido();
        when(repository.findById(id)).thenReturn(Optional.of(itemPedido));

        Optional<ItemPedido> result = itemPedidoRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(itemPedido, result.get());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindAll() {
        ItemPedido itemPedido1 = new ItemPedido();
        ItemPedido itemPedido2 = new ItemPedido();
        List<ItemPedido> itemList = Arrays.asList(itemPedido1, itemPedido2);
        when(repository.findAll()).thenReturn(itemList);

        List<ItemPedido> result = itemPedidoRepository.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(itemList, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        doNothing().when(repository).deleteById(id);

        itemPedidoRepository.delete(id);

        verify(repository, times(1)).deleteById(id);
    }
}