package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.RotaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.RotaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RastrearEntregaUseCase {

    private final RotaRepositoryImpl rotaRepository;

    public RastrearEntregaUseCase(RotaRepositoryImpl rotaRepository) {
        this.rotaRepository = rotaRepository;
    }

    public List<RotaDTO> rastrearRotasPorEntregaId(UUID entregaId) {

        // Recupera as rotas associadas ao entregaId do repositório
        List<Rota> rotas = rotaRepository.findByEntregaId(entregaId);

        // Caso não existam rotas no banco, podemos calcular rotas ou retornar uma mensagem de erro
        if (rotas.isEmpty()) {
            throw new ControllerMessagingException("Ainda não foi iniciado os calculos de rotas para está entrega");
        }

        return rotas.stream().map(RotaMapper::toDTO).collect(Collectors.toList());
    }
}
