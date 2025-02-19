package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.infra.database.repository.jpa.RotaJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RotaRepositoryImplTest {

    @Mock
    private RotaJpaRepository rotaJpaRepository;

    @InjectMocks
    private RotaRepositoryImpl rotaRepository;

    private Rota rota;
    private UUID rotaId;

    @BeforeEach
    void setUp() {
        rotaId = UUID.randomUUID();
        rota = new Rota();
        rota.setId(rotaId);
    }

    @Test
    void testFindById() {
        when(rotaJpaRepository.findById(rotaId)).thenReturn(Optional.of(rota));

        Optional<Rota> foundRota = rotaRepository.findById(rotaId);

        assertTrue(foundRota.isPresent());
        assertEquals(rotaId, foundRota.get().getId());
        verify(rotaJpaRepository, times(1)).findById(rotaId);
    }

    @Test
    void testFindAll() {
        List<Rota> rotaList = List.of(rota);
        when(rotaJpaRepository.findAll()).thenReturn(rotaList);

        List<Rota> foundRotaList = rotaRepository.findAll();

        assertEquals(1, foundRotaList.size());
        assertEquals(rotaId, foundRotaList.get(0).getId());
        verify(rotaJpaRepository, times(1)).findAll();
    }

    @Test
    void testFindByEntregaId() {
        UUID entregaId = UUID.randomUUID();
        List<Rota> rotaList = List.of(rota);
        when(rotaJpaRepository.findByEntregaId(entregaId)).thenReturn(rotaList);

        List<Rota> foundRotaList = rotaRepository.findByEntregaId(entregaId);

        assertEquals(1, foundRotaList.size());
        assertEquals(rotaId, foundRotaList.get(0).getId());
        verify(rotaJpaRepository, times(1)).findByEntregaId(entregaId);
    }

    @Test
    void testSave() {
        when(rotaJpaRepository.save(rota)).thenReturn(rota);

        Rota savedRota = rotaRepository.save(rota);

        assertEquals(rotaId, savedRota.getId());
        verify(rotaJpaRepository, times(1)).save(rota);
    }

    @Test
    void testDeleteById() {
        doNothing().when(rotaJpaRepository).deleteById(rotaId);

        rotaRepository.deleteById(rotaId);

        verify(rotaJpaRepository, times(1)).deleteById(rotaId);
    }
}