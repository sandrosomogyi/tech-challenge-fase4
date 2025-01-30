package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.in.controller;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.usecase.GerenciarProdutoUseCase;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job cargaProdutosJob;

    private final GerenciarProdutoUseCase gerenciarProdutoUseCase;

    @Autowired
    public ProdutoController(GerenciarProdutoUseCase gerenciarProdutoUseCase) {
        this.gerenciarProdutoUseCase = gerenciarProdutoUseCase;
    }

    @PostMapping("/carga-produtos")
    public String cargaProdutos() {
        try {
            jobLauncher.run(cargaProdutosJob, new JobParametersBuilder().toJobParameters());
            return "Carga de produtos iniciada com sucesso!";
        } catch (Exception e) {
            return "Erro ao iniciar carga de produtos: " + e.getMessage();
        }
    }

    @PostMapping
    public ProdutoDTO adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return gerenciarProdutoUseCase.adicionarProduto(produtoDTO);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return gerenciarProdutoUseCase.atualizarProduto(id, produtoDTO);
    }

    @GetMapping
    public List<ProdutoDTO> listarProdutos() {
        return gerenciarProdutoUseCase.listarProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Long id) {
        return gerenciarProdutoUseCase.buscarProdutoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        gerenciarProdutoUseCase.excluirProduto(id);
    }
}
