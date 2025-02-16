package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.PedidoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.*;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.Pedido;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.PedidoStatus;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.mappers.PedidoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriarPedidoUseCase criarPedidoUseCase;

    @MockBean
    private ConsultarPedidoUseCase consultarPedidoUseCase;

    @MockBean
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @MockBean
    private PedidoMapper pedidoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private PedidoDTO pedidoDTO;
    private UUID pedidoId;

    @BeforeEach
    void setUp() {
        pedidoId = UUID.randomUUID();
        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedidoId);
        pedidoDTO.setStatus("PENDENTE");
       

    }

    @Test
    void testCriarPedido() throws Exception {

        PedidoStatus status = PedidoStatus.PENDENTE;
        
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(status); 

        when(criarPedidoUseCase.executar(any(PedidoDTO.class))).thenReturn(pedido);
        when(pedidoMapper.toDTO(pedido)).thenReturn(pedidoDTO);

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedidoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pedidoId.toString()))
                // Comparando com a representação em String do enum (geralmente o nome do valor)
                .andExpect(jsonPath("$.status").value(status.toString()));

        verify(criarPedidoUseCase, times(1)).executar(any(PedidoDTO.class));
    }

    @Test
    void testAtualizarStatusPedido() throws Exception {
        // Supondo que PedidoStatus é um enum, ex: PENDENTE, CONFIRMADO, etc.
        PedidoStatus status = PedidoStatus.PENDENTE;
        
        // Criar um objeto Pedido para retornar no mock
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(status);
        
        // Configuração dos mocks: agora usamos status.toString() para passar uma String
        when(atualizarStatusPedidoUseCase.executar(eq(pedidoId), eq(status.toString()))).thenReturn(pedido);
        when(pedidoMapper.toDTO(pedido)).thenReturn(pedidoDTO);
        
        // Executa a requisição PUT, enviando o status como String
        mockMvc.perform(put("/api/pedidos/atualizar-status")
                .param("pedidoId", pedidoId.toString())
                .param("status", status.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(status.toString()));
        
        // Verifica se o use case foi chamado corretamente, usando status.toString() como parâmetro.
        verify(atualizarStatusPedidoUseCase, times(1)).executar(eq(pedidoId), eq(status.toString()));
    }


    @Test
    void testConsultarPedido() throws Exception {
        Pedido pedido = new Pedido(); 
        pedido.setId(pedidoId); 

        when(consultarPedidoUseCase.executar(pedidoId)).thenReturn(pedido); 
        when(pedidoMapper.toDTO(pedido)).thenReturn(pedidoDTO); 

        mockMvc.perform(get("/api/pedidos/{id}", pedidoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pedidoId.toString()));

        verify(consultarPedidoUseCase, times(1)).executar(pedidoId);
    }

    @Test
    void testListarPedidos() throws Exception {
        List<Pedido> pedidos = List.of(new Pedido()); 

        when(consultarPedidoUseCase.executarTodos()).thenReturn(pedidos); 
        when(pedidoMapper.toDTO(any(Pedido.class))).thenReturn(pedidoDTO); 

        mockMvc.perform(get("/api/pedidos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1));

        verify(consultarPedidoUseCase, times(1)).executarTodos();
    }

}
