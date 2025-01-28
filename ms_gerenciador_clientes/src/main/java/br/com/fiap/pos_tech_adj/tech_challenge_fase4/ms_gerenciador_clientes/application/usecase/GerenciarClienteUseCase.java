package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.repository.ClienteRepository;

import java.util.List;
import java.util.UUID;

public class GerenciarClienteUseCase {
    private final ClienteRepository clienteRepository;

    public GerenciarClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.salvar(cliente);
    }

    public Cliente buscarCliente(UUID id) {
        return clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.buscarTodos();
    }

    public void excluirCliente(UUID id) {
        clienteRepository.excluir(id);
    }
}