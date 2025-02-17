package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler exceptionHandler;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        exceptionHandler = new ControllerExceptionHandler();
        request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/test-endpoint");
    }

    @Test
    void testEntityNotFoundException() {
        ControllerNotFoundException exception = new ControllerNotFoundException("Entity not found");

        ResponseEntity<StandardError> response = exceptionHandler.entityNotFound(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", response.getBody().getError());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }

    @Test
    void testDatabaseException() {
        ControllerDatabaseException exception = new ControllerDatabaseException("Database error");

        ResponseEntity<StandardError> response = exceptionHandler.databaseException(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Database Exception", response.getBody().getError());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }

    @Test
    void testOptimisticLockingException() {
        OptimisticLockingFailureException exception = new OptimisticLockingFailureException("Optimistic Lock error");

        ResponseEntity<StandardError> response = exceptionHandler.optimisticException(exception, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("MongoDB Exception: Documento foi atualizado por outro usuário. Tente Novamente", response.getBody().getError());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }

    @Test
    void testDuplicateKeyException() {
        DuplicateKeyException exception = new DuplicateKeyException("Duplicate key error");

        ResponseEntity<StandardError> response = exceptionHandler.duplicateKeyException(exception, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("MongoDB Exception: Documento já existe na coleção.", response.getBody().getError());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }

    @Test
    void testSQLIntegrityConstraintViolationException() {
        SQLIntegrityConstraintViolationException exception = new SQLIntegrityConstraintViolationException("SQL constraint violation");

        ResponseEntity<StandardError> response = exceptionHandler.sqlIntegrityConstraintViolationException(exception, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Erro no banco de dados", response.getBody().getError());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }

    @Test
    void testMessagingException() {
        ControllerMessagingException exception = new ControllerMessagingException("Messaging error");

        ResponseEntity<StandardError> response = exceptionHandler.messagingException(exception, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Messaging Exception", response.getBody().getError());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }

    @Test
    void testMethodArgumentTypeMismatchException() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getMessage()).thenReturn("Invalid parameter type");

        ResponseEntity<StandardError> response = exceptionHandler.methodArgumentTypeMismatchException(exception, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Invalid Parameter", response.getBody().getError());
        assertEquals("Invalid parameter type", response.getBody().getMessage());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }

    @Test
    void testGenericException() {
        Exception exception = new Exception("Unexpected error");

        ResponseEntity<StandardError> response = exceptionHandler.genericException(exception, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno no servidor", response.getBody().getError());
        assertEquals("Unexpected error", response.getBody().getMessage());
        assertEquals("/test-endpoint", response.getBody().getPath());
    }
}
