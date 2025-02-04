package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.out.repository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.repository.ClienteRepository;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.infra.database.repository.jpa.ClienteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteJpaRepository repository;

    public ClienteRepositoryImpl(ClienteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void excluir(UUID id) {
        repository.deleteById(id);
    }
}
