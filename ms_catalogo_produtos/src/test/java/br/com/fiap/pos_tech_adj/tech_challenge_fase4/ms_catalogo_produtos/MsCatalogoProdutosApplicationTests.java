package br.com.fiap.pos_tech_adj.tech_challenge_fase4.ms_catalogo_produtos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MsCatalogoProdutosApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		// Verifica se o contexto foi carregado corretamente
		assertNotNull(context, "O contexto não deve ser nulo");
	}
}
