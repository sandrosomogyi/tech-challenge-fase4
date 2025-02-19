package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.infra.database.repository.jpa.EntregadorJpaRepository;
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
class EntregadorRepositoryImplTest {

    @Mock
    private EntregadorJpaRepository repository;

    @InjectMocks
    private EntregadorRepositoryImpl entregadorRepository;

    private Entregador entregador;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        entregador = new Entregador();
        entregador.setId(id);
    }

    @Test
    void testFindById() {
        when(repository.findById(id)).thenReturn(Optional.of(entregador));

        Optional<Entregador> result = entregadorRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(entregador, result.get());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindAll() {
        List<Entregador> entregadores = List.of(entregador);
        when(repository.findAll()).thenReturn(entregadores);

        List<Entregador> result = entregadorRepository.findAll();

        assertEquals(entregadores, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(repository.save(entregador)).thenReturn(entregador);

        Entregador result = entregadorRepository.save(entregador);

        assertEquals(entregador, result);
        verify(repository, times(1)).save(entregador);
    }

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(id);

        entregadorRepository.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}