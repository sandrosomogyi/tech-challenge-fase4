package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.infra.database.repository.jpa.EntregaJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntregaRepositoryImplTest {

    @Mock
    private EntregaJpaRepository entregaJpaRepository;

    @InjectMocks
    private EntregaRepositoryImpl entregaRepository;

    private Entrega entrega;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        entrega = new Entrega();
        entrega.setId(id);
    }

    @Test
    void testFindById() {
        when(entregaJpaRepository.findById(id)).thenReturn(Optional.of(entrega));

        Optional<Entrega> result = entregaRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(entrega, result.get());
        verify(entregaJpaRepository, times(1)).findById(id);
    }

    @Test
    void testFindAll() {
        List<Entrega> entregas = List.of(entrega);
        when(entregaJpaRepository.findAll()).thenReturn(entregas);

        List<Entrega> result = entregaRepository.findAll();

        assertEquals(entregas, result);
        verify(entregaJpaRepository, times(1)).findAll();
    }

    @Test
    void testFindByPedidoId() {
        when(entregaJpaRepository.findByPedidoId(id)).thenReturn(Optional.of(entrega));

        Optional<Entrega> result = entregaRepository.findByPedidoId(id);

        assertTrue(result.isPresent());
        assertEquals(entrega, result.get());
        verify(entregaJpaRepository, times(1)).findByPedidoId(id);
    }

    @Test
    void testFindByStatus() {
        StatusEntrega status = StatusEntrega.PENDENTE;
        List<Entrega> entregas = List.of(entrega);
        when(entregaJpaRepository.findByStatus(status)).thenReturn(entregas);

        List<Entrega> result = entregaRepository.findByStatus(status);

        assertEquals(entregas, result);
        verify(entregaJpaRepository, times(1)).findByStatus(status);
    }

    @Test
    void testFindByCodigoRastreio() {
        String codigoRastreio = "123456";
        when(entregaJpaRepository.findByCodigoRastreio(codigoRastreio)).thenReturn(Optional.of(entrega));

        Optional<Entrega> result = entregaRepository.findByCodigoRastreio(codigoRastreio);

        assertTrue(result.isPresent());
        assertEquals(entrega, result.get());
        verify(entregaJpaRepository, times(1)).findByCodigoRastreio(codigoRastreio);
    }

    @Test
    void testSave() {
        when(entregaJpaRepository.save(entrega)).thenReturn(entrega);

        Entrega result = entregaRepository.save(entrega);

        assertEquals(entrega, result);
        verify(entregaJpaRepository, times(1)).save(entrega);
    }

    @Test
    void testDeleteById() {
        doNothing().when(entregaJpaRepository).deleteById(id);

        entregaRepository.deleteById(id);

        verify(entregaJpaRepository, times(1)).deleteById(id);
    }
}