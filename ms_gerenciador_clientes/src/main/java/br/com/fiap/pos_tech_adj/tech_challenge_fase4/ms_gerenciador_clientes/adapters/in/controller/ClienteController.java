package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.usecase.GerenciarClienteUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.mappers.ClienteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final GerenciarClienteUseCase clienteUseCase;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteController(GerenciarClienteUseCase clienteUseCase, ClienteMapper clienteMapper) {
        this.clienteUseCase = clienteUseCase;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        var cliente = clienteMapper.toEntity(clienteDTO);
        var clienteCriado = clienteUseCase.criarCliente(cliente);
        return ResponseEntity.status(201).body(clienteMapper.toDTO(clienteCriado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> alterarCliente(@PathVariable UUID id, @RequestBody ClienteDTO clienteDTO) {
        var cliente = clienteMapper.toEntity(clienteDTO);
        var clienteCriado = clienteUseCase.alterarCliente(id, cliente);
        return ResponseEntity.ok(clienteMapper.toDTO(clienteCriado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable UUID id) {
        var cliente = clienteUseCase.buscarCliente(id);
        return ResponseEntity.ok(clienteMapper.toDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        var clientes = clienteUseCase.listarClientes();
        return ResponseEntity.ok(clienteMapper.toDTOList(clientes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable UUID id) {
        clienteUseCase.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
