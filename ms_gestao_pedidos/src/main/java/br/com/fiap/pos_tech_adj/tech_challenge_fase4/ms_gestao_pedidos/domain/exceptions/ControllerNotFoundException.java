package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException (String message){
        super(message);
    }
}
