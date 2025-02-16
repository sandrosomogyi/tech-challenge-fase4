package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EntregaControllerTest {

    @Mock
    private CriarEntregaUseCase criarEntregaUseCase;

    @Mock
    private FinalizarEntregaUseCase finalizarEntregaUseCase;

    @Mock
    private CancelarEntregaUseCase cancelarEntregaUseCase;

    @Mock
    private AtribuirEntregadorUseCase atribuirEntregadorUseCase;

    @Mock
    private BuscarEntregaUseCase buscarEntregaUseCase;

    @InjectMocks
    private EntregaController entregaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarEntrega() {
        EntregaDTO entregaDTO = new EntregaDTO();
        when(criarEntregaUseCase.executar(any(EntregaDTO.class))).thenReturn(entregaDTO);

        ResponseEntity<EntregaDTO> response = entregaController.criarEntrega(entregaDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(entregaDTO, response.getBody());
    }

    @Test
    void testFinalizarEntrega() {
        UUID id = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(finalizarEntregaUseCase.executar(id)).thenReturn(entregaDTO);

        ResponseEntity<EntregaDTO> response = entregaController.finalizarEntrega(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(entregaDTO, response.getBody());
    }

    @Test
    void testCancelarEntrega() {
        UUID id = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(cancelarEntregaUseCase.executar(id)).thenReturn(entregaDTO);

        ResponseEntity<EntregaDTO> response = entregaController.cancelarEntrega(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(entregaDTO, response.getBody());
    }

    @Test
    void testAtribuirEntregador() {
        UUID entregaId = UUID.randomUUID();
        UUID entregadorId = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(atribuirEntregadorUseCase.executar(entregaId, entregadorId)).thenReturn(entregaDTO);

        ResponseEntity<EntregaDTO> response = entregaController.atribuirEntregador(entregaId, entregadorId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(entregaDTO, response.getBody());
    }

    @Test
    void testBuscarEntregaPorId() {
        UUID id = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(buscarEntregaUseCase.buscarEntregaPorId(id)).thenReturn(Optional.of(entregaDTO));

        ResponseEntity<EntregaDTO> response = entregaController.buscarEntregaPorId(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(entregaDTO, response.getBody());
    }

    @Test
    void testBuscarEntregaPorIdNotFound() {
        UUID id = UUID.randomUUID();
        when(buscarEntregaUseCase.buscarEntregaPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<EntregaDTO> response = entregaController.buscarEntregaPorId(id);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testListarEntregas() {
        List<EntregaDTO> entregas = List.of(new EntregaDTO(), new EntregaDTO());
        when(buscarEntregaUseCase.buscarEntregas()).thenReturn(entregas);

        ResponseEntity<List<EntregaDTO>> response = entregaController.listarEntregas();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(entregas, response.getBody());
    }

    @Test
    void testHandleRuntimeException() {
        RuntimeException exception = new RuntimeException("Internal Server Error");
        ResponseEntity<String> response = entregaController.handleRuntimeException(exception);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Internal Server Error", response.getBody());
    }
}