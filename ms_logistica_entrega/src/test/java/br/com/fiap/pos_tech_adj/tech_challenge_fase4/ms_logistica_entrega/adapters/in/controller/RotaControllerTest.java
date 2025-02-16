package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CalcularRotaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.RastrearEntregaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RotaControllerTest {

    @Mock
    private CalcularRotaUseCase calcularRotaUseCase;

    @Mock
    private RastrearEntregaUseCase rastrearEntregaUseCase;

    @InjectMocks
    private RotaController rotaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularRota() {
        Map<String, String> params = new HashMap<>();
        params.put("entregaId", UUID.randomUUID().toString());
        params.put("origem", "São Paulo");
        params.put("destino", "Rio de Janeiro");

        RotaDTO rotaDTO = new RotaDTO();
        when(calcularRotaUseCase.calcularMelhorRota(any(UUID.class), any(String.class), any(String.class))).thenReturn(rotaDTO);

        ResponseEntity<RotaDTO> response = rotaController.calcularRota(params);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rotaDTO, response.getBody());
    }

    @Test
    public void testRastrearEntrega() {
        UUID entregaId = UUID.randomUUID();
        List<RotaDTO> rotas = new ArrayList<>();
        when(rastrearEntregaUseCase.rastrearRotasPorEntregaId(entregaId)).thenReturn(rotas);

        ResponseEntity<List<RotaDTO>> response = rotaController.rastrearEntrega(entregaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rotas, response.getBody());
    }

    @Test
    public void testListar() {
        String codigoRastreio = "123456";
        List<RotaDTO> rotas = new ArrayList<>();
        when(rastrearEntregaUseCase.rastrearRotasPorCodigoRastreio(codigoRastreio)).thenReturn(rotas);

        ResponseEntity<List<RotaDTO>> response = rotaController.listar(codigoRastreio);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rotas, response.getBody());
    }

    @Test
    public void testHandleRuntimeException() {
        RuntimeException ex = new RuntimeException("Internal Server Error");

        ResponseEntity<String> response = rotaController.handleRuntimeException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", response.getBody());
    }
}