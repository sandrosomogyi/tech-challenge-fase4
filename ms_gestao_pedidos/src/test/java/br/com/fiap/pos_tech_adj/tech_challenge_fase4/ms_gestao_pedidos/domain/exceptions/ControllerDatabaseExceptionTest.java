package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControllerDatabaseExceptionTest {

    @Test
    void testExceptionWithMessage() {
        String errorMessage = "Erro no banco de dados";
        
        ControllerDatabaseException exception = new ControllerDatabaseException(errorMessage);

        assertThat(exception)
            .isInstanceOf(RuntimeException.class)
            .hasMessage(errorMessage)
            .hasNoCause();
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String errorMessage = "Erro no banco de dados";
        Throwable cause = new RuntimeException("Causa original");

        ControllerDatabaseException exception = new ControllerDatabaseException(errorMessage, cause);

        assertThat(exception)
            .isInstanceOf(RuntimeException.class)
            .hasMessage(errorMessage)
            .hasCause(cause);
    }
}
