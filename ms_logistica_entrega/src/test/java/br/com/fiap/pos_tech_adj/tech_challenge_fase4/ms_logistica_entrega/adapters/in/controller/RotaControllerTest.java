package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.RotaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.CalcularRotaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.RastrearEntregaUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RotaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CalcularRotaUseCase calcularRotaUseCase;

    @Mock
    private RastrearEntregaUseCase rastrearEntregaUseCase;

    @InjectMocks
    private RotaController rotaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rotaController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testCalcularRota() throws Exception {
        RotaDTO rotaDTO = new RotaDTO();
        when(calcularRotaUseCase.calcularMelhorRota(any(UUID.class), anyString(), anyString())).thenReturn(rotaDTO);

        String jsonContent = "{\"entregaId\":\"" + UUID.randomUUID() + "\",\"origem\":\"Origem\",\"destino\":\"Destino\"}";

        mockMvc.perform(post("/api/rotas/calcular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().json("{}")); 
    }

    @Test
    public void testCalcularRotaErro() throws Exception {
        when(calcularRotaUseCase.calcularMelhorRota(any(UUID.class), anyString(), anyString())).thenThrow(new RuntimeException("Erro simulado ao calcular rota"));

        String jsonContent = "{\"entregaId\":\"" + UUID.randomUUID() + "\",\"origem\":\"Origem\",\"destino\":\"Destino\"}";

        mockMvc.perform(post("/api/rotas/calcular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erro simulado ao calcular rota"));
    }

    @Test
    public void testRastrearEntrega() throws Exception {
        UUID entregaId = UUID.randomUUID();
        LocalDateTime dataSaida = LocalDateTime.of(2025, 2, 9, 10, 0, 30, 123456789); 
        
        RotaDTO rota1 = new RotaDTO();
        rota1.setId(UUID.randomUUID());
        rota1.setEntregaId(entregaId);
        rota1.setPontoPartida("Origem 1");
        rota1.setDestino("Destino 1");
        rota1.setDistancia(10.0);
        rota1.setTempoEstimado(30.0);
        rota1.setDataSaida(dataSaida);

        RotaDTO rota2 = new RotaDTO();
        rota2.setId(UUID.randomUUID());
        rota2.setEntregaId(entregaId);
        rota2.setPontoPartida("Origem 2");
        rota2.setDestino("Destino 2");
        rota2.setDistancia(20.0);
        rota2.setTempoEstimado(60.0);
        rota2.setDataSaida(dataSaida);

        List<RotaDTO> rotas = Arrays.asList(rota1, rota2);
        when(rastrearEntregaUseCase.rastrearRotasPorEntregaId(any(UUID.class))).thenReturn(rotas);
        
        mockMvc.perform(get("/api/rotas/rastrear/" + entregaId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(rota1.getId().toString()));
            
    }

    @Test
    public void testRastrearEntregaErro() throws Exception {
        UUID entregaId = UUID.randomUUID();
        when(rastrearEntregaUseCase.rastrearRotasPorEntregaId(any(UUID.class))).thenThrow(new RuntimeException("Erro simulado ao rastrear entrega"));

        mockMvc.perform(get("/api/rotas/rastrear/" + entregaId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erro simulado ao rastrear entrega"));
    }

    @Test
    public void testListarPorCodigoRastreio() throws Exception {
        String codigoRastreio = "ABC123";
        LocalDateTime dataSaida = LocalDateTime.of(2025, 2, 9, 10, 0, 30, 123456789);
    
        RotaDTO rota1 = new RotaDTO();
        rota1.setId(UUID.randomUUID());
        rota1.setEntregaId(UUID.randomUUID());
        rota1.setPontoPartida("Origem 1");
        rota1.setDestino("Destino 1");
        rota1.setDistancia(10.0);
        rota1.setTempoEstimado(30.0);
        rota1.setDataSaida(dataSaida);

        RotaDTO rota2 = new RotaDTO();
        rota2.setId(UUID.randomUUID());
        rota2.setEntregaId(UUID.randomUUID());
        rota2.setPontoPartida("Origem 2");
        rota2.setDestino("Destino 2");
        rota2.setDistancia(20.0);
        rota2.setTempoEstimado(60.0);
        rota2.setDataSaida(dataSaida);

        List<RotaDTO> rotas = Arrays.asList(rota1, rota2);
        when(rastrearEntregaUseCase.rastrearRotasPorCodigoRastreio(anyString())).thenReturn(rotas);

           mockMvc.perform(get("/api/rotas/codigo-rastreio/" + codigoRastreio))
            .andExpect(status().isOk())
            .andExpect(content().json("[{},{}]"));
    }

    @Test
    public void testListarPorCodigoRastreioErro() throws Exception {
        String codigoRastreio = "ABC123";
        when(rastrearEntregaUseCase.rastrearRotasPorCodigoRastreio(anyString())).thenThrow(new RuntimeException("Erro simulado ao listar por código de rastreio"));

        mockMvc.perform(get("/api/rotas/codigo-rastreio/" + codigoRastreio))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erro simulado ao listar por código de rastreio"));
    }
}