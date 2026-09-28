# Trabalho de Banco de Dados — Fase 1
## Sistema de Gerenciamento de Cinema

**Domínio de informação:** Cinema (rede/sala de exibição de filmes)

---

## 1. Introdução

O trabalho tem como objetivo o desenvolvimento de uma aplicação para o gerenciamento de um cinema, envolvendo o cadastro de filmes, salas, funcionários e clientes, além do controle das sessões e da venda de ingressos.

No sistema, os filmes são relacionados às sessões, que acontecem em salas específicas, em determinados dias e horários. Cada sessão possui um preço e um funcionário responsável. Os clientes podem comprar ingressos para as sessões, escolhendo o assento e a forma de pagamento.

O banco de dados foi criado para representar essas relações. Um filme pode ter várias sessões, assim como uma sala pode receber várias sessões ao longo do tempo. Um funcionário também pode ser responsável por várias sessões. Já os clientes podem comprar ingressos para diferentes sessões, e cada sessão pode ter vários ingressos vendidos.

A aplicação foi desenvolvida em Java e funciona por meio de uma interface em modo texto (console). Nela, é possível cadastrar, consultar, alterar e excluir (CRUD) funcionários, filmes, salas e clientes. Também é possível realizar o agendamento das sessões e a venda de ingressos. Além disso, o sistema possui relatórios que permitem consultar informações relacionadas a diferentes tabelas do banco de dados.


---

## 4. Operações da Aplicação

### 4.1 CRUD das tabelas de entidade
- Funcionário — cadastrar, listar, atualizar, remover
- Filme — cadastrar, listar, atualizar, remover
- Sala — cadastrar, listar, atualizar, remover
- Cliente — cadastrar, listar, atualizar, remover

### 4.2 Processos de negócio das tabelas associativas
- **Agendar sessão** (tabela `sessao`): relaciona filme, sala e funcionário, validando conflito de horário na sala.
- **Vender ingresso** (tabela `ingresso`): relaciona sessão e cliente, validando que o assento ainda não foi vendido.

### 4.3 Relatórios (mínimo de 3 exigido — foram implementados 4)
1. **Sessões de um filme em um período**, com sala e funcionário responsável (junção de `sessao`, `filme`, `sala`, `funcionario`).
2. **Faturamento por sessão**: quantidade de ingressos vendidos e valor total arrecadado por sessão (junção de `sessao`, `filme`, `ingresso`).
3. **Clientes que mais compraram ingressos** (ranking), com total de ingressos e valor gasto (junção de `cliente`, `ingresso`).
4. **Ocupação das salas por sessão**, percentual de assentos vendidos em relação à capacidade (junção de `sala`, `sessao`, `filme`, `ingresso`).

---

## 5. Repositório / Diretório do Projeto

> *(Link a preencher pela equipe, conforme exigido pelo enunciado — o diretório
> deve conter: código-fonte, arquivo de backup do banco de dados e um arquivo
> de texto com instruções de compilação/execução, sem itens compactados.)*

**Link:** `______________________________________________`

Conteúdo do diretório entregue:
- `src/` — código-fonte da aplicação Java
- `banco/schema.sql` — script de criação das tabelas (DDL)
- `banco/dados.sql` — script de dados iniciais (INSERTs)
- `banco/cinema.db` — arquivo de backup do banco de dados (SQLite), já populado
- `lib/sqlite-jdbc-3.46.1.3.jar` — driver JDBC necessário
- `instrucoes.txt` — instruções de compilação e execução
