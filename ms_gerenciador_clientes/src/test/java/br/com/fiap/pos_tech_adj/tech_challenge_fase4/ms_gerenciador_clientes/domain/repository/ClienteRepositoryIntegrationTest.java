package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ClienteRepositoryIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void testSalvar() {

        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setEmail("joao.silva@gmail.com");
        cliente.setEndereco("Rua 13 de maio, 123");
        cliente.setTelefone("11987654321");

        Cliente clienteSalvo = clienteRepository.salvar(cliente);

        assertNotNull(clienteSalvo.getId());
        assertEquals(cliente.getNome(), clienteSalvo.getNome());
        assertEquals(cliente.getEmail(), clienteSalvo.getEmail());
        assertEquals(cliente.getEndereco(), clienteSalvo.getEndereco());
        assertEquals(cliente.getTelefone(), clienteSalvo.getTelefone());
    }

    @Test
    void testBuscarPorId() {

        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setEmail("maria.sousa@gmail.com");
        cliente.setEndereco("Rua 25 de marco, 123");
        cliente.setTelefone("11987654322");
        Cliente clienteSalvo = clienteRepository.salvar(cliente);

        Optional<Cliente> clienteBuscado = clienteRepository.buscarPorId(clienteSalvo.getId());

        assertTrue(clienteBuscado.isPresent());
        assertEquals(clienteSalvo.getId(), clienteBuscado.get().getId());
        assertEquals(cliente.getNome(), clienteBuscado.get().getNome());
        assertEquals(cliente.getEmail(), clienteBuscado.get().getEmail());
        assertEquals(cliente.getEndereco(), clienteBuscado.get().getEndereco());
        assertEquals(cliente.getTelefone(), clienteBuscado.get().getTelefone());
    }

    @Test
    void testBuscarTodos() {
        Cliente cliente = new Cliente();
        cliente.setNome("jose");
        cliente.setEmail("jose.andrade@gmail.com");
        cliente.setEndereco("Rua Brasil, 2020");
        cliente.setTelefone("11977654322");
        clienteRepository.salvar(cliente);

        Optional<Cliente> clienteBuscado = clienteRepository.buscarTodos().stream().findFirst();
        assertTrue(clienteBuscado.isPresent());
    }

    @Test
    void testExcluir() {
        Cliente cliente = new Cliente();
        cliente.setNome("marta");
        cliente.setEmail("marta@gmail.com");
        cliente.setEndereco("Rua das aves, 1323");
        cliente.setTelefone("11974654322");
        cliente.setId(clienteRepository.salvar(cliente).getId());
        clienteRepository.excluir(cliente.getId());

        Optional<Cliente> clienteBuscado = clienteRepository.buscarPorId(cliente.getId());
        assertFalse(clienteBuscado.isPresent());
    }
}