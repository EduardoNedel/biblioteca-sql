-- Exercício 2 - Modelagem do banco de dados (PostgreSQL)
-- Sistema de Biblioteca

-- Ordem: item e usuario primeiro, porque emprestimo depende das duas

CREATE TABLE item (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo      VARCHAR(10)  NOT NULL UNIQUE,
    titulo      VARCHAR(150) NOT NULL,
    tipo        VARCHAR(10)  NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor       VARCHAR(100),             -- só faz sentido para livro
    edicao      INTEGER,                  -- só faz sentido para revista
    disponivel  BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    tipo         VARCHAR(10)  NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INTEGER      NOT NULL CHECK (limite_itens > 0)
);

CREATE TABLE emprestimo (
    id                      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id                 INTEGER NOT NULL REFERENCES item(id),
    usuario_id              INTEGER NOT NULL REFERENCES usuario(id),
    data_retirada           DATE    NOT NULL,
    data_devolucao_prevista DATE    NOT NULL,
    data_devolucao          DATE,                      -- fica NULL até o item voltar
    valor_multa             NUMERIC(8,2) NOT NULL DEFAULT 0
);


-- Dados de teste

INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
    ('L001', 'Java: Como Programar', 'livro',   'Paul Deitel',      NULL, TRUE),
    ('L002', 'Código Limpo',         'livro',   'Robert C. Martin', NULL, TRUE),
    ('L003', 'Dom Casmurro',         'livro',   'Machado de Assis', NULL, TRUE),
    ('R001', 'Superinteressante',    'revista', NULL,               450,  FALSE),
    ('R002', 'Mundo Estranho',       'revista', NULL,               210,  TRUE);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
    ('Ana Souza',   'aluno',     3),
    ('Carlos Lima', 'professor', 5);

-- Empréstimo 1: já devolvido, 5 dias de atraso (livro: 5 x R$ 0,50 = R$ 2,50)
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES (
    (SELECT id FROM item WHERE codigo = 'L001'),
    (SELECT id FROM usuario WHERE nome = 'Ana Souza'),
    '2026-10-01', '2026-10-15', '2026-10-20', 2.50
);

-- Empréstimo 2: em aberto (revista, prazo de 7 dias)
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES (
    (SELECT id FROM item WHERE codigo = 'R001'),
    (SELECT id FROM usuario WHERE nome = 'Carlos Lima'),
    '2026-10-28', '2026-11-04', NULL, 0
);
