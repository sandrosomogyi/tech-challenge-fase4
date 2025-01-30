package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.infra.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(DataSource dataSource, PlatformTransactionManager transactionManager) {
        this.dataSource = dataSource;
        this.transactionManager = transactionManager;
    }

    /**
     * Configura o JobRepository para armazenar metadados de execução do Batch.

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager);
        factory.setIsolationLevelForCreate("ISOLATION_DEFAULT");
        factory.setTablePrefix("BATCH_"); // Define o prefixo das tabelas do Spring Batch
        factory.afterPropertiesSet();
        return factory.getObject();
    }
     */

    /**
     * Configura o JobLauncher utilizando TaskExecutorJobLauncher.

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor()); // Permite execução assíncrona
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
     */

    /**
     * Configura o JobExplorer para consultar metadados de execução do Batch.

    @Bean
    public JobExplorer jobExplorer() throws Exception {
        JobExplorerFactoryBean factory = new JobExplorerFactoryBean();
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
     */
}
