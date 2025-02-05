package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.Rota;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerMessagingException;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.RotaRepository;

import java.util.List;
import java.util.UUID;

public class RastrearEntregaUseCase {

    private final RotaRepository rotaRepository;

    public RastrearEntregaUseCase(RotaRepository rotaRepository) {
        this.rotaRepository = rotaRepository;
    }

    public List<Rota> rastrearRotasPorEntregaId(UUID entregaId) {

        // Recupera as rotas associadas ao entregaId do repositório
        List<Rota> rotas = rotaRepository.findByEntregaId(entregaId);

        // Caso não existam rotas no banco, podemos calcular rotas ou retornar uma mensagem de erro
        if (rotas.isEmpty()) {
            throw new ControllerMessagingException("Ainda não foi iniciado os calculos de rotas para está entraga");
        }

        return rotas;
    }
}
