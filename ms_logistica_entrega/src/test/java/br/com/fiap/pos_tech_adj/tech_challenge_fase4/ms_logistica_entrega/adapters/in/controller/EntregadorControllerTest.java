package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.dto.EntregadorDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.usecase.GerenciarEntregadorUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.domain.exceptions.ControllerExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EntregadorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GerenciarEntregadorUseCase gerenciarEntregadorUseCase;

    @InjectMocks
    private EntregadorController entregadorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(entregadorController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testCadastrarEntregador() throws Exception {
        EntregadorDTO entregadorDTO = new EntregadorDTO();
        entregadorDTO.setNome("João");
        entregadorDTO.setTelefone("999999999");
        entregadorDTO.setVeiculo("Moto");

        when(gerenciarEntregadorUseCase.cadastrarEntregador(any(EntregadorDTO.class))).thenReturn(entregadorDTO);

        String jsonContent = "{\"nome\":\"João\",\"telefone\":\"999999999\",\"veiculo\":\"Moto\"}";

        mockMvc.perform(post("/api/entregadores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    public void testBuscarEntregadorPorId() throws Exception {
        UUID id = UUID.randomUUID();
        EntregadorDTO entregadorDTO = new EntregadorDTO();
        entregadorDTO.setNome("João");
        entregadorDTO.setTelefone("999999999");
        entregadorDTO.setVeiculo("Moto");

        when(gerenciarEntregadorUseCase.buscarEntregadorPorId(any(UUID.class))).thenReturn(Optional.of(entregadorDTO));

        mockMvc.perform(get("/api/entregadores/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"nome\":\"João\",\"telefone\":\"999999999\",\"veiculo\":\"Moto\"}"));
    }

    @Test
    public void testBuscarEntregadorPorIdNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(gerenciarEntregadorUseCase.buscarEntregadorPorId(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/entregadores/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAtualizarEntregador() throws Exception {
        UUID id = UUID.randomUUID();
        EntregadorDTO entregadorDTO = new EntregadorDTO();
        entregadorDTO.setNome("João Atualizado");
        entregadorDTO.setTelefone("999999999");
        entregadorDTO.setVeiculo("Carro");

        when(gerenciarEntregadorUseCase.atualizarEntregador(any(UUID.class), any(EntregadorDTO.class))).thenReturn(Optional.of(entregadorDTO));

        String jsonContent = "{\"nome\":\"João Atualizado\",\"telefone\":\"999999999\",\"veiculo\":\"Carro\"}";

        mockMvc.perform(put("/api/entregadores/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    public void testAtualizarEntregadorNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(gerenciarEntregadorUseCase.atualizarEntregador(any(UUID.class), any(EntregadorDTO.class))).thenReturn(Optional.empty());

        String jsonContent = "{\"nome\":\"João Atualizado\",\"telefone\":\"999999999\",\"veiculo\":\"Carro\"}";

        mockMvc.perform(put("/api/entregadores/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRemoverEntregador() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/entregadores/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListarEntregadores() throws Exception {
        List<EntregadorDTO> entregadores = Arrays.asList(new EntregadorDTO(), new EntregadorDTO());
        when(gerenciarEntregadorUseCase.buscarEntregadores()).thenReturn(entregadores);

        mockMvc.perform(get("/api/entregadores"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCadastrarEntregadorErro() throws Exception {
        when(gerenciarEntregadorUseCase.cadastrarEntregador(any(EntregadorDTO.class))).thenThrow(new RuntimeException("Erro simulado ao cadastrar entregador"));

        String jsonContent = "{\"nome\":\"João\",\"telefone\":\"999999999\",\"veiculo\":\"Moto\"}";

        mockMvc.perform(post("/api/entregadores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erro simulado ao cadastrar entregador"));
    }
}