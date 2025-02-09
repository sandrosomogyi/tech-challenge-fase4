package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregaDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.*;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.entity.StatusEntrega;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.repository.EntregaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EntregaControllerTest {

    @Mock
    private CriarEntregaUseCase criarEntregaUseCase;

    @Mock
    private FinalizarEntregaUseCase finalizarEntregaUseCase;

    @Mock
    private CancelarEntregaUseCase cancelarEntregaUseCase;

    @Mock
    private AtribuirEntregadorUseCase atribuirEntregadorUseCase;

    @Mock
    private BuscarEntregaUseCase buscarEntregaUseCase;

    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private EntregaController entregaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(entregaController).build();
    }

    @Test
    public void testCriarEntrega() throws Exception {
        UUID id = UUID.randomUUID();
        UUID pedidoId = UUID.randomUUID();
        UUID entregadorId = UUID.randomUUID();
        String enderecoDestino = "Rua Exemplo, 123";
        StatusEntrega status = StatusEntrega.PENDENTE;
        LocalDateTime dataHoraPrevista = LocalDateTime.now().plusDays(1);
        LocalDateTime dataHoraConclusao = null;
        String codigoRastreio = "ABC123";

        EntregaDTO entregaDTO = new EntregaDTO(id, pedidoId, entregadorId, enderecoDestino, status, dataHoraPrevista, dataHoraConclusao, codigoRastreio);

        when(criarEntregaUseCase.executar(any(EntregaDTO.class))).thenReturn(entregaDTO);

        String jsonContent = String.format(
                "{\"id\":\"%s\",\"pedidoId\":\"%s\",\"entregadorId\":\"%s\",\"enderecoDestino\":\"%s\",\"status\":\"%s\",\"dataHoraPrevista\":\"%s\",\"dataHoraConclusao\":%s,\"codigoRastreio\":\"%s\"}",
                id, pedidoId, entregadorId, enderecoDestino, status, dataHoraPrevista, dataHoraConclusao, codigoRastreio);

        mockMvc.perform(post("/api/entregas")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonContent))
                .andExpect(status().isCreated());
    }


    @Test
    public void testFinalizarEntrega() throws Exception {
        UUID id = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(finalizarEntregaUseCase.executar(any(UUID.class))).thenReturn(entregaDTO);

        mockMvc.perform(put("/api/entregas/finalizar/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelarEntrega() throws Exception {
        UUID id = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(cancelarEntregaUseCase.executar(any(UUID.class))).thenReturn(entregaDTO);

        mockMvc.perform(put("/api/entregas/cancelar/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testAtribuirEntregador() throws Exception {
        UUID entregaId = UUID.randomUUID();
        UUID entregadorId = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(atribuirEntregadorUseCase.executar(any(UUID.class), any(UUID.class))).thenReturn(entregaDTO);

        mockMvc.perform(put("/api/entregas/atribuir-entregador")
                .param("entregaId", entregaId.toString())
                .param("entregadorId", entregadorId.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarEntregaPorId() throws Exception {
        UUID id = UUID.randomUUID();
        EntregaDTO entregaDTO = new EntregaDTO();
        when(buscarEntregaUseCase.buscarEntregaPorId(any(UUID.class))).thenReturn(Optional.of(entregaDTO));

        mockMvc.perform(get("/api/entregas/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarEntregas() throws Exception {
        List<EntregaDTO> entregas = Arrays.asList(new EntregaDTO(), new EntregaDTO());
        when(buscarEntregaUseCase.buscarEntregas()).thenReturn(entregas);

        mockMvc.perform(get("/api/entregas"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCriarEntregaErro() throws Exception {
    when(criarEntregaUseCase.executar(any(EntregaDTO.class))).thenThrow(new RuntimeException("Erro simulado ao criar entrega"));

    String jsonContent = "{\"id\":\"1\",\"pedidoId\":\"1\",\"entregadorId\":\"1\",\"enderecoDestino\":\"Rua Exemplo, 123\",\"status\":\"PENDENTE\",\"dataHoraPrevista\":\"2025-02-10T10:00:00\",\"dataHoraConclusao\":null,\"codigoRastreio\":\"ABC123\"}";

    mockMvc.perform(post("/api/entregas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
            .andExpect(status().isInternalServerError());
    }

    @Test
    public void testBuscarEntregasErro() throws Exception {
    when(buscarEntregaUseCase.buscarEntregas()).thenThrow(new RuntimeException("Erro simulado ao acessar o repositório"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
        buscarEntregaUseCase.buscarEntregas();
    });

    assertEquals("Erro simulado ao acessar o repositório", exception.getMessage());
    }
    
    @Test
    public void testBuscarEntregaPorIdErro() throws Exception {
        when(buscarEntregaUseCase.buscarEntregaPorId(any(UUID.class))).thenReturn(Optional.empty());

        UUID id = UUID.randomUUID();
        mockMvc.perform(get("/api/entregas/" + id))
                .andExpect(status().isNotFound());
    }

}