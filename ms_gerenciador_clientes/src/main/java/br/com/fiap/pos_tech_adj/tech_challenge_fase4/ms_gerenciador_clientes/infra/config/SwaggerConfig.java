package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_gerenciador_clientes.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tech Challenge Fase 4 API MS Gerenciador de Clientes")
                        .version("1.0")
                        .description("Documentação da API para o projeto Tech Challenge Fase 4")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .email("seuemail@dominio.com")
                                .url("https://www.fiap.com.br")
                        ));
    }
}
