package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.application.usecase.ConsultarItensPedidoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gestao_pedidos.domain.entity.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/item-pedido")
public class ItemPedidoController {

    private final ConsultarItensPedidoUseCase consultarItensPedidoUseCase;

    @Autowired
    public ItemPedidoController(ConsultarItensPedidoUseCase consultarItensPedidoUseCase) {
        this.consultarItensPedidoUseCase = consultarItensPedidoUseCase;
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<ItemPedido>> consultarItensPorPedido(@PathVariable UUID pedidoId) {
        try {
            // Chama o caso de uso para consultar os itens do pedido
            List<ItemPedido> itensPedido = consultarItensPedidoUseCase.executar(pedidoId);

            if (itensPedido.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 se não houver itens
            }

            return ResponseEntity.ok(itensPedido); // Retorna 200 com a lista de itens
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o pedido não for encontrado
        }
    }
}
