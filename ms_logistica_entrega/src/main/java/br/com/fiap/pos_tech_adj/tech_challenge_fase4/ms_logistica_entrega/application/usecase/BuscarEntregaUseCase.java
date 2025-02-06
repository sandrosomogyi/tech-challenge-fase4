package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregadorMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BuscarEntregaUseCase {

    private final EntregaRepositoryImpl entregaRepository;

    public BuscarEntregaUseCase(EntregaRepositoryImpl entregaRepository){
        this.entregaRepository = entregaRepository;
    }

    public Optional<EntregaDTO> buscarEntregaPorId(UUID entregaId) {
        return entregaRepository.findById(entregaId)
                .map(EntregaMapper::toDTO);
    }

    public List<EntregaDTO> buscarEntregas() {
        return entregaRepository.findAll()
                .stream()
                .map(EntregaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
