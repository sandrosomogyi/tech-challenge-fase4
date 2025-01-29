package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException (String message){
        super(message);
    }
}
