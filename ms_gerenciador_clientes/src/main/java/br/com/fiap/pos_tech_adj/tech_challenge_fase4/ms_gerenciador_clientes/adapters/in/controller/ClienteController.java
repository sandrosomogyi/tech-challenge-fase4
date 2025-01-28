package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.usecase.GerenciarClienteUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.mappers.ClienteMapper;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final GerenciarClienteUseCase clienteUseCase;
    private final ClienteMapper clienteMapper;

    public ClienteController(GerenciarClienteUseCase clienteUseCase, ClienteMapper clienteMapper) {
        this.clienteUseCase = clienteUseCase;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    public ClienteDTO criarCliente(@RequestBody ClienteDTO clienteDTO) {
        var cliente = clienteMapper.toEntity(clienteDTO);
        var clienteCriado = clienteUseCase.criarCliente(cliente);
        return clienteMapper.toDTO(clienteCriado);
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarCliente(@PathVariable UUID id) {
        var cliente = clienteUseCase.buscarCliente(id);
        return clienteMapper.toDTO(cliente);
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        var clientes = clienteUseCase.listarClientes();
        return clienteMapper.toDTOList(clientes);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable UUID id) {
        clienteUseCase.excluirCliente(id);
    }
}
