package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.service;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.dto.ClienteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ClienteService clienteService;

    private UUID clienteId;
    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteId = UUID.randomUUID();
        clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteId);
        clienteDTO.setNome("John Doe");
    }

    @Test
    public void testGetClienteById() {
        String url = "http://localhost:8082/api/clientes/{id}";
        when(restTemplate.getForObject(eq(url), eq(ClienteDTO.class), eq(clienteId))).thenReturn(clienteDTO);

        ClienteDTO result = clienteService.getClienteById(clienteId);

        assertEquals(clienteDTO, result);
    }


}