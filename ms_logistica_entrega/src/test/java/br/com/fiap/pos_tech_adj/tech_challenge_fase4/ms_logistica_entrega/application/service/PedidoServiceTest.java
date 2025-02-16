package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_logistica_entrega.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    public void testAtualizarStatusPedido_Success() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
                .thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));

        boolean result = pedidoService.atualizarStatusPedido("123", "ENTREGUE");

        assertTrue(result);
    }

    @Test
    public void testAtualizarStatusPedido_Failure() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
                .thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(500)));

        boolean result = pedidoService.atualizarStatusPedido("123", "ENTREGUE");

        assertFalse(result);
    }

    @Test
    public void testAtualizarStatusPedido_Exception() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(Class.class)))
                .thenThrow(new RuntimeException());

        boolean result = pedidoService.atualizarStatusPedido("123", "ENTREGUE");

        assertFalse(result);
    }
}