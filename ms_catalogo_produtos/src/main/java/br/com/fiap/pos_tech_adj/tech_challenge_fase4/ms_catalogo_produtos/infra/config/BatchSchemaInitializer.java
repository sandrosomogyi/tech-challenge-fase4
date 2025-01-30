package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos.infra.config;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BatchSchemaInitializer {

    private final DataSource dataSource;

    public BatchSchemaInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initializeSchema() {
        if (!tablesExist()) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator(
                    new ClassPathResource("schema-mysql.sql") // Ou schema-postgresql.sql
            );
            populator.execute(dataSource);
            System.out.println("Tabelas do Spring Batch criadas com sucesso.");
        } else {
            System.out.println("Tabelas do Spring Batch já existem. Nenhuma ação necessária.");
        }
    }

    /**
     * Verifica se as tabelas do Spring Batch já existem no banco de dados.
     *
     * @return true se as tabelas existirem, false caso contrário.
     */
    private boolean tablesExist() {
        String[] batchTables = {
                "BATCH_JOB_INSTANCE",
                "BATCH_JOB_EXECUTION",
                "BATCH_STEP_EXECUTION",
                "BATCH_JOB_EXECUTION_PARAMS",
                "BATCH_JOB_EXECUTION_CONTEXT",
                "BATCH_STEP_EXECUTION_CONTEXT"
        };

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            for (String table : batchTables) {
                try (ResultSet tables = metaData.getTables(null, null, table, null)) {
                    if (!tables.next()) {
                        System.out.println("Tabela não encontrada: " + table);
                        return false;
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao verificar tabelas: " + e.getMessage());
            return false;
        }
    }
}