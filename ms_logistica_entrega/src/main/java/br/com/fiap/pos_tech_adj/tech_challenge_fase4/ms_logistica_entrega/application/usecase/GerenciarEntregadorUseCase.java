package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregadorRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregadorMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entregador;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GerenciarEntregadorUseCase {

    private final EntregadorRepositoryImpl entregadorRepository;

    public GerenciarEntregadorUseCase(EntregadorRepositoryImpl entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public EntregadorDTO cadastrarEntregador(EntregadorDTO entregadorDTO) {
        Entregador entregador = EntregadorMapper.toEntity(entregadorDTO);
        Entregador novoEntregador = entregadorRepository.save(entregador);
        return EntregadorMapper.toDTO(novoEntregador);
    }

    public Optional<EntregadorDTO> buscarEntregadorPorId(UUID entregadorId) {
        return entregadorRepository.findById(entregadorId)
                .map(EntregadorMapper::toDTO);
    }

    public List<EntregadorDTO> buscarEntregadores() {
        return entregadorRepository.findAll()
                .stream()
                .map(EntregadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EntregadorDTO> atualizarEntregador(UUID entregadorId, EntregadorDTO entregadorDTO) {
        entregadorRepository.findById(entregadorId)
                .orElseThrow(() -> new ControllerNotFoundException("Entregador não encontrado."));

        Entregador entregadorAtualizado = EntregadorMapper.toEntity(entregadorDTO);
        entregadorAtualizado.setId(entregadorId);
        return Optional.of(EntregadorMapper.toDTO(entregadorRepository.save(entregadorAtualizado)));
    }

    public void removerEntregador(UUID entregadorId) {
        entregadorRepository.findById(entregadorId)
                .orElseThrow(() -> new ControllerNotFoundException("Entregador não encontrado."));

        entregadorRepository.deleteById(entregadorId);
    }
}
