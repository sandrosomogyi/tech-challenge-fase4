package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.RotaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.RotaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.RotaService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CalcularRotaUseCase {

    private final RotaService rotaService;
    private final RotaRepositoryImpl rotaRepository;

    public CalcularRotaUseCase(RotaRepositoryImpl rotaRepository, RotaService rotaService) {
        this.rotaService = rotaService;
        this.rotaRepository = rotaRepository;
    }

    public RotaDTO calcularMelhorRota(UUID entregaId, String origem, String destino) {
        // Obtendo a melhor rota através de um serviço externo
        Rota rotaCalculada = rotaService.calcularMelhorRota(entregaId, origem, destino);

        if ( rotaCalculada == null ) {
            throw new ControllerMessagingException("Não foi possivel calcular a rota.");
        }

        var rotaSalva = rotaRepository.save(rotaCalculada);

        return RotaMapper.toDTO(rotaSalva);
    }
}
