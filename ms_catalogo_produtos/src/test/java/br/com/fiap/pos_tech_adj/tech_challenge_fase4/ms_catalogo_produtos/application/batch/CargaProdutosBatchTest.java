package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.batch;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mappers.ProdutoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.out.repository.ProdutoRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CargaProdutosBatchTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ProdutoRepositoryImpl produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @Mock
    private PlatformTransactionManager transactionManager;

    @InjectMocks
    private CargaProdutosBatch cargaProdutosBatch;

    @Test
    public void testCargaProdutosJob() {
        Job job = cargaProdutosBatch.cargaProdutosJob();
        assertNotNull(job);
        assertEquals("cargaProdutosJob", job.getName());
    }

    @Test
    public void testCargaProdutosStep() {
        Step step = cargaProdutosBatch.cargaProdutosStep();
        assertNotNull(step);
        assertEquals("cargaProdutosStep", step.getName());
    }

    @Test
    public void testProdutoReader() {
        FlatFileItemReader<ProdutoDTO> reader = cargaProdutosBatch.produtoReader();
        assertNotNull(reader);
    }


    @Test
    public void testProdutoProcessor() {
        ItemProcessor<ProdutoDTO, Produto> processor = cargaProdutosBatch.produtoProcessor();
        assertNotNull(processor);
    }

    @Test
    public void testProdutoWriter() {
        ItemWriter<Produto> writer = cargaProdutosBatch.produtoWriter();
        assertNotNull(writer);
    }
}
