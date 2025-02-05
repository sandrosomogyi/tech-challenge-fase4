package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.mappers.RotaMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.RotaRepository;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service.RotaService;

import java.util.UUID;

public class CalcularRotaUseCase {

    private final RotaService rotaService;
    private final RotaRepository rotaRepository;

    public CalcularRotaUseCase(RotaRepository rotaRepository, RotaService rotaService) {
        this.rotaService = rotaService;
        this.rotaRepository = rotaRepository;
    }

    public RotaDTO calcularMelhorRota(UUID entregaId, String origem, String destino) {
        // Obtendo a melhor rota através de um serviço externo
        Rota rotaCalculada = rotaService.calcularMelhorRota(entregaId, origem, destino);

        var rotaSalva = rotaRepository.save(rotaCalculada);

        return RotaMapper.toDTO(rotaSalva);
    }
}
