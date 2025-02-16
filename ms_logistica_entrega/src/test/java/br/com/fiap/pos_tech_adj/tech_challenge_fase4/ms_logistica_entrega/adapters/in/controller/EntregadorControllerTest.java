package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.GerenciarEntregadorUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EntregadorControllerTest {

    @Mock
    private GerenciarEntregadorUseCase gerenciarEntregadorUseCase;

    @InjectMocks
    private EntregadorController entregadorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarEntregador() {
        EntregadorDTO entregadorDTO = new EntregadorDTO();
        when(gerenciarEntregadorUseCase.cadastrarEntregador(any(EntregadorDTO.class))).thenReturn(entregadorDTO);

        ResponseEntity<EntregadorDTO> response = entregadorController.cadastrarEntregador(entregadorDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entregadorDTO, response.getBody());
    }

    @Test
    void testBuscarEntregadorPorId() {
        UUID id = UUID.randomUUID();
        EntregadorDTO entregadorDTO = new EntregadorDTO();
        when(gerenciarEntregadorUseCase.buscarEntregadorPorId(id)).thenReturn(Optional.of(entregadorDTO));

        ResponseEntity<EntregadorDTO> response = entregadorController.buscarEntregadorPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entregadorDTO, response.getBody());
    }

    @Test
    void testBuscarEntregadorPorIdNotFound() {
        UUID id = UUID.randomUUID();
        when(gerenciarEntregadorUseCase.buscarEntregadorPorId(id)).thenReturn(Optional.empty());

        ResponseEntity<EntregadorDTO> response = entregadorController.buscarEntregadorPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAtualizarEntregador() {
        UUID id = UUID.randomUUID();
        EntregadorDTO entregadorDTO = new EntregadorDTO();
        when(gerenciarEntregadorUseCase.atualizarEntregador(any(UUID.class), any(EntregadorDTO.class))).thenReturn(Optional.of(entregadorDTO));

        ResponseEntity<EntregadorDTO> response = entregadorController.atualizarEntregador(id, entregadorDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entregadorDTO, response.getBody());
    }

    @Test
    void testAtualizarEntregadorNotFound() {
        UUID id = UUID.randomUUID();
        EntregadorDTO entregadorDTO = new EntregadorDTO();
        when(gerenciarEntregadorUseCase.atualizarEntregador(any(UUID.class), any(EntregadorDTO.class))).thenReturn(Optional.empty());

        ResponseEntity<EntregadorDTO> response = entregadorController.atualizarEntregador(id, entregadorDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testRemoverEntregador() {
        UUID id = UUID.randomUUID();
        doNothing().when(gerenciarEntregadorUseCase).removerEntregador(id);

        ResponseEntity<Void> response = entregadorController.removerEntregador(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testListarEntregadores() {
        List<EntregadorDTO> entregadores = Arrays.asList(new EntregadorDTO(), new EntregadorDTO());
        when(gerenciarEntregadorUseCase.buscarEntregadores()).thenReturn(entregadores);

        ResponseEntity<List<EntregadorDTO>> response = entregadorController.listarEntregadores();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entregadores, response.getBody());
    }

    @Test
    void testHandleRuntimeException() {
        RuntimeException ex = new RuntimeException("Error message");

        ResponseEntity<String> response = entregadorController.handleRuntimeException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error message", response.getBody());
    }
}