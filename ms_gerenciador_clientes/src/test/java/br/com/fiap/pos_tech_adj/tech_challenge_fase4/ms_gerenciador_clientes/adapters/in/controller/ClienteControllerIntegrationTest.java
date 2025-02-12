package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.dto.ClienteDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.application.usecase.GerenciarClienteUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.domain.entity.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    GerenciarClienteUseCase clienteUseCase;

    @Test
    void testCriarCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Maria Santos");
        clienteDTO.setEmail("maria@gmail.com");
        clienteDTO.setTelefone("11922221111");
        clienteDTO.setEndereco("Avenida Brasil, São Roque, SP");

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value("Maria Santos"))
                .andExpect(jsonPath("$.email").value("maria@gmail.com"))
                .andExpect(jsonPath("$.telefone").value("11922221111"))
                .andExpect(jsonPath("$.endereco").value("Avenida Brasil, São Roque, SP"));
    }

    @Test
    void testAlterarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Pedro Silva");
        cliente.setEmail("pedro@gmail.com");
        cliente.setTelefone("11922221114");
        cliente.setEndereco("Avenida Radial Leste, São Paulo, SP");
        cliente.setId(clienteUseCase.criarCliente(cliente).getId());

        ClienteDTO clienteAlteradoDTO = new ClienteDTO();
        clienteAlteradoDTO.setNome("Pedro Silva Alterado");
        clienteAlteradoDTO.setEmail("pedro.alterado@gmail.com");
        clienteAlteradoDTO.setTelefone("11933332222");
        clienteAlteradoDTO.setEndereco("Avenida Paulista, São Paulo, SP");

        mockMvc.perform(put("/api/clientes/" + cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteAlteradoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente.getId().toString()))
                .andExpect(jsonPath("$.nome").value("Pedro Silva Alterado"))
                .andExpect(jsonPath("$.email").value("pedro.alterado@gmail.com"))
                .andExpect(jsonPath("$.telefone").value("11933332222"))
                .andExpect(jsonPath("$.endereco").value("Avenida Paulista, São Paulo, SP"));
    }

    @Test
    void testBuscarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Jose Silva");
        cliente.setEmail("jose@gmail.com");
        cliente.setTelefone("11922221115");
        cliente.setEndereco("Avenida Radial Leste, São Paulo, SP");
        cliente.setId(clienteUseCase.criarCliente(cliente).getId());

        mockMvc.perform(get("/api/clientes/" + cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente.getId().toString()))
                .andExpect(jsonPath("$.nome").value("Jose Silva"))
                .andExpect(jsonPath("$.email").value("jose@gmail.com"))
                .andExpect(jsonPath("$.telefone").value("11922221115"))
                .andExpect(jsonPath("$.endereco").value("Avenida Radial Leste, São Paulo, SP"));
    }

    @Test
    void testListarClientes() throws Exception {
        mockMvc.perform(get("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testExcluirCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Paulo Andrade");
        cliente.setEmail("paulo@gmail.com");
        cliente.setTelefone("11922221116");
        cliente.setEndereco("Avenida Sousa Bandeira, São Paulo, SP");
        cliente.setId(clienteUseCase.criarCliente(cliente).getId());

        mockMvc.perform(delete("/api/clientes/" + cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
