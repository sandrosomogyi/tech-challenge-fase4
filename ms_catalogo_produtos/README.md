# Tech Challenger - FASE 4 🚀🚀

## Projeto do Tech Challenge da Pós Tech FIAP - Arquitetura e Desenvolvimento Java (Fase 4)

Este documento fornece um guia detalhado para a execução dos testes unitários e de integração do projeto, além da geração de relatórios utilizando **Allure** e **JaCoCo**.

---

## 📌 Requisitos
Antes de executar os testes e gerar relatórios, certifique-se de que:
- Você possui o **Java 17+** instalado e configurado corretamente no ambiente.
- O **Maven** está instalado e configurado.
- O **Allure** está instalado para visualização dos relatórios.
- O projeto está devidamente clonado e configurado.

---

## ▶️ Executar o Projeto
Para executar o projeto localmente, siga os passos abaixo:

1. Clone o repositório:
   ```bash
   git https://github.com/sandrosomogyi/tech-challenge-fase4.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd ms_catalogo_produtos
   ```
3. Compile e construa o projeto com Maven:
   ```bash
   mvn clean install -U
   ```
4. Inicie a aplicação localmente:
   ```bash
   mvn spring-boot:run
   ```

---

## 🛠️ Execução dos Testes

### Passo a passo para rodar os testes
1. Abra o terminal.
2. Navegue até o diretório raiz do projeto:
   ```bash
   cd /ms_catalogo_produtos
   ```
3. Execute todos os testes unitários e de integração:
   ```bash
   mvn test
   ```
   Esse comando executará todos os testes configurados no projeto.

---

## 📊 Relatório Allure
O **Allure** fornece relatórios detalhados sobre a execução dos testes.

### Gerando e visualizando o relatório Allure
1. Certifique-se de que o **Allure** está instalado em seu sistema.
2. Após a execução dos testes, gere e visualize o relatório com o comando:
   ```bash
   allure open target/allure-report
   ```
3. Para servir o relatório diretamente no navegador, utilize:
   ```bash
   allure serve target/allure-results
   ```

---

## 📈 Relatório JaCoCo
O **JaCoCo** é uma ferramenta para medir a cobertura de testes no código.

### Gerando o relatório JaCoCo
1. Certifique-se de que o plugin **JaCoCo** está configurado no arquivo `pom.xml`.
2. Execute o comando abaixo para gerar o relatório de cobertura:
   ```bash
   mvn test jacoco:report
   ```
3. O relatório será gerado no seguinte diretório:
   ```
   target/site/jacoco/index.html
   ```
4. Abra o arquivo `index.html` no navegador para visualizar o relatório completo.

---

## ✅ Conclusão
Seguindo este guia, você conseguirá:
✔️ Executar os testes do projeto.
✔️ Gerar relatórios detalhados com **Allure**.
✔️ Analisar a cobertura de testes utilizando **JaCoCo**.
✔️ Garantir a qualidade e a confiabilidade do software.

Caso encontre problemas ou tenha dúvidas, consulte a documentação do projeto ou entre em contato com a equipe.

🚀 **Bons testes e sucesso no projeto!**
