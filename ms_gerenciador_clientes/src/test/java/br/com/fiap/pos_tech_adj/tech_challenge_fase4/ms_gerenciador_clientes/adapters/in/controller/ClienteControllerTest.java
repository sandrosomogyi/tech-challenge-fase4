package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.mappers.ClienteMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.usecase.GerenciarClienteUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteControllerTest {

    @Mock
    private GerenciarClienteUseCase clienteUseCase;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        Cliente cliente = new Cliente();
        Cliente clienteCriado = new Cliente();

        when(clienteMapper.toEntity(any(ClienteDTO.class))).thenReturn(cliente);
        when(clienteUseCase.criarCliente(any(Cliente.class))).thenReturn(clienteCriado);
        when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = clienteController.criarCliente(clienteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
    }

    @Test
    void testAlterarCliente() {
        UUID id = UUID.randomUUID();
        ClienteDTO clienteDTO = new ClienteDTO();
        Cliente cliente = new Cliente();
        Cliente clienteAlterado = new Cliente();
        ClienteDTO clienteDTOAlterado = new ClienteDTO();

        when(clienteMapper.toEntity(any(ClienteDTO.class))).thenReturn(cliente);
        when(clienteUseCase.alterarCliente(any(UUID.class), any(Cliente.class))).thenReturn(clienteAlterado);
        when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(clienteDTOAlterado);

        ResponseEntity<ClienteDTO> response = clienteController.alterarCliente(id, clienteDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTOAlterado, response.getBody());
    }

    @Test
    void testBuscarCliente() {
        UUID id = UUID.randomUUID();
        Cliente cliente = new Cliente();
        ClienteDTO clienteDTO = new ClienteDTO();

        when(clienteUseCase.buscarCliente(any(UUID.class))).thenReturn(cliente);
        when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(clienteDTO);

        ResponseEntity<ClienteDTO> response = clienteController.buscarCliente(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
    }

    @Test
    void testListarClientes() {
        List<Cliente> clientes = List.of(new Cliente(), new Cliente());
        List<ClienteDTO> clientesDTO = List.of(new ClienteDTO(), new ClienteDTO());

        when(clienteUseCase.listarClientes()).thenReturn(clientes);
        when(clienteMapper.toDTOList(any(List.class))).thenReturn(clientesDTO);

        ResponseEntity<List<ClienteDTO>> response = clienteController.listarClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientesDTO, response.getBody());
    }

    @Test
    void testExcluirCliente() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = clienteController.excluirCliente(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}