package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.batch;

import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.mappers.ProdutoMapper;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.adapters.out.repository.ProdutoRepositoryImpl;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.application.dto.ProdutoDTO;
import br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.domain.entity.Produto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class CargaProdutosBatch {

    private final ProdutoRepositoryImpl produtoRepository;
    private final ProdutoMapper produtoMapper;

    public CargaProdutosBatch(ProdutoRepositoryImpl produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Bean
    public Job cargaProdutosJob() {
        return new JobBuilder("cargaProdutosJob")
                .incrementer(new RunIdIncrementer())
                .start(cargaProdutosStep())
                .build();
    }

    @Bean
    public Step cargaProdutosStep() {
        return new StepBuilder("cargaProdutosStep")
                .<ProdutoDTO, Produto>chunk(10)
                .reader(produtoReader())
                .processor(produtoProcessor())
                .writer(produtoWriter())
                .build();
    }

    @Bean
    public ItemReader<ProdutoDTO> produtoReader() {
        FlatFileItemReader<ProdutoDTO> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("produtos.csv"));
        reader.setLineMapper(new DefaultLineMapper<ProdutoDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer());
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ProdutoDTO>() {{
                setTargetType(ProdutoDTO.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<ProdutoDTO, Produto> produtoProcessor() {
        return produtoMapper::toEntity;
    }

    @Bean
    public ItemWriter<Produto> produtoWriter() {
        return items -> produtoRepository.saveAll(items.getItems());  // Salva os produtos no banco
    }
}
