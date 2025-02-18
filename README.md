# Tech Challenger - FASE 4 🚀🚀

Projeto do Tech Challenge da Pós Tech FIAP - Arquitetura e Desenvolvimento Java (Fase 4)
Este documento fornece uma visão geral do projeto e aponta para as documentações detalhadas disponíveis nos READMEs individuais de cada microserviço.

---

## Projeto de Gestão de Pedidos e Entregas

Este projeto é composto por quatro microsserviços que trabalham juntos para gerenciar pedidos e entregas. Cada microsserviço é responsável por uma parte específica do fluxo de processamento de pedidos e entregas.

## Microsserviços

1. **Gestão de Pedidos**
   - Responsável pela criação e atualização de pedidos.
   - Envia eventos para o Kafka quando um pedido é criado ou atualizado.

2. **Logística de Entregas**
   - Consome eventos do Kafka para criar e gerenciar entregas.
   - Atualiza o status das entregas e envia eventos de atualização para o Kafka.

3. **Atribuição de Entregadores**
   - Atribui entregadores às entregas.
   - Atualiza o status das entregas para "EM_TRANSITO" e envia eventos para o Kafka.

4. **Finalização de Entregas**
   - Gerencia a conclusão das entregas.
   - Atualiza o status das entregas para "CONCLUIDA" e envia eventos para o Kafka.

## Fluxo de Processamento

1. **Criação de Pedido**
   - Um pedido é criado no microsserviço de Gestão de Pedidos.
   - Um evento é enviado para o Kafka.

2. **Criação de Entrega**
   - O microsserviço de Logística de Entregas consome o evento do Kafka e cria uma entrega associada ao pedido.

3. **Atribuição de Entregador**
   - Quando um entregador é atribuído à entrega, o status da entrega e do pedido é atualizado para "EM_TRANSITO".
   - Um evento é enviado para o Kafka.

4. **Finalização da Entrega**
   - Quando a entrega é concluída, o status da entrega é atualizado para "CONCLUIDA".
   - Um evento é enviado para o Kafka e o status do pedido é atualizado para "CONCLUIDO".

## 📌 Requisitos
Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

Java 17+: Necessário para rodar os microserviços baseados em Spring Boot.

Maven: Para construir e gerenciar dependências do projeto.

Docker e Docker Compose: Para gerenciar e rodar os contêineres.

Postman ou Insomnia: Para interação com os serviços.

---

## ▶️ Executar o Projeto
Para executar o projeto localmente, siga os passos abaixo:

Clone o repositório:

```bash
git clone https://github.com/sandrosomogyi/tech-challenge-fase4.git
cd tech-challenge-fase4
 ```
##Consulte os READMEs individuais de cada microserviço para instruções detalhadas:

- ms_gerenciador_clientes: [README.md](https://github.com/sandrosomogyi/tech-challenge-fase4/blob/main/ms_gerenciador_clientes/README.md)

- ms_catalogo_produtos: [README.md](https://github.com/sandrosomogyi/tech-challenge-fase4/blob/main/ms_catalogo_produtos/README.md)

- ms_gestao_pedidos: [README.md](https://github.com/sandrosomogyi/tech-challenge-fase4/blob/main/ms_gestao_pedidos/README.md)
  
- ms_logistica_entrega: [README.md](https://github.com/sandrosomogyi/tech-challenge-fase4/blob/main/README.md)

---

## 🛠️ Execução dos Testes
Para informações sobre a execução de testes e a geração de relatórios, consulte os READMEs dos microserviços relevantes.

---

## 🌐 Usando o Postman
Instruções detalhadas para a configuração e uso do Postman também estão disponíveis na documentação do projeto na página da entrega do projeto no site da Fiap.

---

## ✅ Conclusão
Seguindo os guias nos READMEs de cada microserviço, você conseguirá: 
✔️ Executar os testes do projeto. 
✔️ Gerar relatórios detalhados. 
✔️ Garantir a qualidade e a confiabilidade do software.

---
Caso encontre problemas ou tenha dúvidas, consulte a documentação do projeto ou entre em contato com a equipe.

🚀** Bons testes e sucesso no projeto!**



