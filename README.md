# Sistema de Biblioteca (Java + SQL)

Atividade do curso Full Stack: lista de tarefas de Java (orientação a objetos) e banco de dados.

## O que tem aqui

**Exercício 1 – Java (`src/`)**
- `ItemBiblioteca` (classe abstrata), com `Livro` e `Revista`. Cada tipo define seu prazo de devolução e a multa por dia.
- `Usuario` (classe abstrata), com `Aluno` (limite de 3 itens) e `Professor` (limite de 5 itens).
- `Biblioteca`: cadastra itens, faz empréstimos e devoluções, calcula multa e lista o acervo.
- `Main`: programa de teste.

**Exercícios 2 e 3 – SQL (`sql/`)**
- `exercicio2_modelagem.sql`: criação das tabelas `item`, `usuario` e `emprestimo` e inserção de dados de teste.
- `exercicio3_consultas.sql`: quatro consultas (acervo, empréstimos em aberto, multas por usuário e itens nunca emprestados).

## Como rodar o Java

Precisa do JDK instalado (testado com Java 17 ou superior). Na pasta do projeto:

```bash
mkdir out
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

## Como rodar o SQL

Os scripts foram escritos para PostgreSQL. Rode primeiro o de modelagem e depois o de consultas:

```bash
psql -d nome_do_banco -f sql/exercicio2_modelagem.sql
psql -d nome_do_banco -f sql/exercicio3_consultas.sql
```

## Autor

Eduardo Nedel
