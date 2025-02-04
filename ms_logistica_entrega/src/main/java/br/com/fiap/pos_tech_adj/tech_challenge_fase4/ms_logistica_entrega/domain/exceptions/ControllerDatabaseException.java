package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions;

public class ControllerDatabaseException extends RuntimeException {

    public ControllerDatabaseException(String message) {
        super(message);
    }

    public ControllerDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}