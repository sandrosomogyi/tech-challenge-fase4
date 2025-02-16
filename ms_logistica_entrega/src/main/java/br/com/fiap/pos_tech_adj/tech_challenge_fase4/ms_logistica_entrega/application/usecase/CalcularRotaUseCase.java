package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.EntregaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.out.repository.RotaRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.RotaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.RotaService;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CalcularRotaUseCase {

    private final RotaService rotaService;
    private final RotaRepositoryImpl rotaRepository;
    private final EntregaRepositoryImpl entregaRepository;

    public CalcularRotaUseCase(RotaRepositoryImpl rotaRepository,
                               EntregaRepositoryImpl entregaRepository,
                               RotaService rotaService) {
        this.rotaService = rotaService;
        this.entregaRepository = entregaRepository;
        this.rotaRepository = rotaRepository;
    }

    public RotaDTO calcularMelhorRota(UUID entregaId, String origem, String destino) {
        //Verifica se entrega existe
        entregaRepository.findById(entregaId)
                .orElseThrow(() -> new ControllerNotFoundException("Entrega não encontrado."));

        // Obtendo a melhor rota através de um serviço externo
        Rota rotaCalculada = rotaService.calcularMelhorRota(entregaId, origem, destino);

        if ( rotaCalculada == null ) {
            throw new ControllerMessagingException("Não foi possivel calcular a rota.");
        }

        Rota rotaSalva = rotaRepository.save(rotaCalculada);

        return RotaMapper.toDTO(rotaSalva);
    }

   
}
