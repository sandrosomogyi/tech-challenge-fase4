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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @Mock
    private ConsultarPedidoUseCase consultarPedidoUseCase;

    @Mock
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private ObjectMapper objectMapper;

    private PedidoDTO pedidoDTO;
    private UUID pedidoId;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
        pedidoId = UUID.randomUUID();
        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedidoId);
        pedidoDTO.setStatus("PENDENTE");
        objectMapper = new ObjectMapper();
    }


    @Test
    void testCriarPedido() throws Exception {
        Pedido pedido = createPedido();
        when(criarPedidoUseCase.executar(any(PedidoDTO.class))).thenReturn(pedido);
        when(pedidoMapper.toDTO(pedido)).thenReturn(pedidoDTO);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pedidoId.toString()))
                .andExpect(jsonPath("$.status").value(PedidoStatus.PENDENTE.toString()));

        verify(criarPedidoUseCase, times(1)).executar(any(PedidoDTO.class));
    }

    @Test
    void testAtualizarStatusPedido() throws Exception {
        Pedido pedido = createPedido();
        when(atualizarStatusPedidoUseCase.executar(eq(pedidoId), eq(PedidoStatus.PENDENTE.toString()))).thenReturn(pedido);
        when(pedidoMapper.toDTO(pedido)).thenReturn(pedidoDTO);

        mockMvc.perform(put("/api/pedidos/atualizar-status")
                        .param("pedidoId", pedidoId.toString())
                        .param("status", PedidoStatus.PENDENTE.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(PedidoStatus.PENDENTE.toString()));

        verify(atualizarStatusPedidoUseCase, times(1)).executar(eq(pedidoId), eq(PedidoStatus.PENDENTE.toString()));
    }

    @Test
    void testConsultarPedido() throws Exception {
        Pedido pedido = createPedido();
        when(consultarPedidoUseCase.executar(pedidoId)).thenReturn(pedido);
        when(pedidoMapper.toDTO(pedido)).thenReturn(pedidoDTO);

        mockMvc.perform(get("/api/pedidos/{id}", pedidoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pedidoId.toString()));

        verify(consultarPedidoUseCase, times(1)).executar(pedidoId);
    }

    @Test
    void testListarPedidos() throws Exception {
        List<Pedido> pedidos = List.of(createPedido());
        when(consultarPedidoUseCase.executarTodos()).thenReturn(pedidos);
        when(pedidoMapper.toDTO(any(Pedido.class))).thenReturn(pedidoDTO);

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(consultarPedidoUseCase, times(1)).executarTodos();
    }

    private Pedido createPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(PedidoStatus.PENDENTE);
        return pedido;
    }
}
