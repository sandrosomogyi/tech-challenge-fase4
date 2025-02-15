package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.repository.ClienteRepository;
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

class GerenciarClienteUseCaseTest {

    private static final UUID CLIENTE_ID = UUID.randomUUID();
    private static final String NOME_TESTE = "Pedro Silva";
    private static final String EMAIL_TESTE = "pedro@gmail.com";
    private static final String TELEFONE_TESTE = "11922221111";
    private static final String ENDERECO_TESTE = "Avenida Brasil, São roque, SP";

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private GerenciarClienteUseCase gerenciarClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(CLIENTE_ID);
        cliente.setNome(NOME_TESTE);
        cliente.setEmail(EMAIL_TESTE);
        cliente.setTelefone(TELEFONE_TESTE);
        cliente.setEndereco(ENDERECO_TESTE);
        return cliente;
    }

    private void assertCliente(Cliente actual, Cliente expected) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNome(), actual.getNome());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getTelefone(), actual.getTelefone());
        assertEquals(expected.getEndereco(), actual.getEndereco());
    }

    @Test
    void testCriarCliente() {
        Cliente cliente = criarCliente();

        when(clienteRepository.salvar(cliente)).thenReturn(cliente);

        Cliente result = gerenciarClienteUseCase.criarCliente(cliente);

        assertCliente(result, cliente);
        verify(clienteRepository, times(1)).salvar(cliente);
    }

    @Test
    void testAlterarCliente() {
        Cliente clienteSalvo = criarCliente();
        Cliente clienteAtualizar = criarCliente();
        clienteAtualizar.setNome("Pedro Santos");
        clienteAtualizar.setEmail("pedroca@gmail.com");

        when(clienteRepository.buscarPorId(CLIENTE_ID)).thenReturn(Optional.of(clienteSalvo));
        when(clienteRepository.salvar(clienteSalvo)).thenReturn(clienteSalvo);

        Cliente result = gerenciarClienteUseCase.alterarCliente(CLIENTE_ID, clienteAtualizar);

        assertCliente(result, clienteAtualizar);

        verify(clienteRepository, times(1)).buscarPorId(CLIENTE_ID);
        verify(clienteRepository, times(1)).salvar(clienteSalvo);
    }

    @Test
    void testBuscarCliente() {
        Cliente cliente = criarCliente();

        when(clienteRepository.buscarPorId(CLIENTE_ID)).thenReturn(Optional.of(cliente));

        Cliente result = gerenciarClienteUseCase.buscarCliente(CLIENTE_ID);

        assertCliente(result, cliente);

        verify(clienteRepository, times(1)).buscarPorId(CLIENTE_ID);
    }

    @Test
    void testListarClientes() {
        Cliente cliente1 = criarCliente();
        Cliente cliente2 = criarCliente();
        cliente2.setId(UUID.randomUUID());

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.buscarTodos()).thenReturn(clientes);

        List<Cliente> result = gerenciarClienteUseCase.listarClientes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(cliente1.getNome(), result.get(0).getNome());
        assertEquals(cliente1.getEmail(), result.get(0).getEmail());
        assertEquals(cliente1.getTelefone(), result.get(0).getTelefone());
        assertEquals(cliente1.getEndereco(), result.get(0).getEndereco());

        verify(clienteRepository, times(1)).buscarTodos();
    }

    @Test
    void testExcluirCliente() {

        doNothing().when(clienteRepository).excluir(CLIENTE_ID);

        gerenciarClienteUseCase.excluirCliente(CLIENTE_ID);

        verify(clienteRepository, times(1)).excluir(CLIENTE_ID);
    }
}