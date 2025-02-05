package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.EntregaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Entrega;
import org.springframework.stereotype.Service;


@Service
public class CriarEntregaUseCase {

    private final EntregaRepositoryImpl entregaRepository;

    public CriarEntregaUseCase(EntregaRepositoryImpl entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public EntregaDTO executar(EntregaDTO entregaDTO) {
        Entrega entregaSalva = entregaRepository.save(EntregaMapper.toEntity(entregaDTO));
        return EntregaMapper.toDTO(entregaSalva);
    }
}
