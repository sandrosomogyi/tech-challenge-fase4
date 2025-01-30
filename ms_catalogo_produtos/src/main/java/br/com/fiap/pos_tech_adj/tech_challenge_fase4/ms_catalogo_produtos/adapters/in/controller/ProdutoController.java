package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase.GerenciarEstoqueProdutoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase.GerenciarProdutoUseCase;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.exceptions.ControllerMessagingException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private JobLauncher jobLauncher;
    private Job cargaProdutosJob;

    private final GerenciarProdutoUseCase gerenciarProdutoUseCase;
    private final GerenciarEstoqueProdutoUseCase gerenciarEstoqueProdutoUseCase;

    @Autowired
    public ProdutoController(GerenciarProdutoUseCase gerenciarProdutoUseCase, GerenciarEstoqueProdutoUseCase gerenciarEstoqueProdutoUseCase) {
        this.gerenciarProdutoUseCase = gerenciarProdutoUseCase;
        this.gerenciarEstoqueProdutoUseCase = gerenciarEstoqueProdutoUseCase;
    }

    @PostMapping("/carga-produtos")
    public ResponseEntity<String> cargaProdutos() {
        try {
            jobLauncher.run(cargaProdutosJob, new JobParametersBuilder().toJobParameters());
            return ResponseEntity.ok("Carga de produtos iniciada com sucesso!");
        } catch (Exception e) {
            throw new ControllerMessagingException("Erro ao iniciar carga: " + e.getMessage());
        }
    }

    @PutMapping("/acrescentar-estoque/{id}")
    public ResponseEntity<ProdutoDTO> acrescentarEstoqueProduto(
            @PathVariable Long id,
            @RequestParam("quantidade") Integer quantidade) {
        return ResponseEntity.ok(gerenciarEstoqueProdutoUseCase.acrescentarEstoqueProduto(id, quantidade));
    }

    @PutMapping("/subtrair-estoque/{id}")
    public ResponseEntity<ProdutoDTO> subtrairEstoqueProduto(
            @PathVariable Long id,
            @RequestParam("quantidade") Integer quantidade) {
        return ResponseEntity.ok(gerenciarEstoqueProdutoUseCase.subtrairEstoqueProduto(id, quantidade));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.status(201).body(gerenciarProdutoUseCase.adicionarProduto(produtoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(gerenciarProdutoUseCase.atualizarProduto(id, produtoDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        return ResponseEntity.ok(gerenciarProdutoUseCase.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(gerenciarProdutoUseCase.buscarProdutoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        gerenciarProdutoUseCase.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
