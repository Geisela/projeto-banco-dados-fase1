-- Esquema Relacional - Sistema de Cinema
-- Banco: SQLite

PRAGMA foreign_keys = ON;

CREATE TABLE funcionario (
    id_funcionario  INTEGER PRIMARY KEY AUTOINCREMENT,
    nome            TEXT    NOT NULL,
    cpf             TEXT    NOT NULL UNIQUE,
    cargo           TEXT    NOT NULL,
    telefone        TEXT
);

CREATE TABLE filme (
    id_filme                INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo                  TEXT    NOT NULL,
    genero                  TEXT    NOT NULL,
    duracao_minutos         INTEGER NOT NULL,
    classificacao_indicativa TEXT   NOT NULL,
    sinopse                 TEXT
);

CREATE TABLE sala (
    id_sala     INTEGER PRIMARY KEY AUTOINCREMENT,
    numero      INTEGER NOT NULL UNIQUE,
    capacidade  INTEGER NOT NULL,
    tipo_sala   TEXT    NOT NULL
);

CREATE TABLE cliente (
    id_cliente  INTEGER PRIMARY KEY AUTOINCREMENT,
    nome        TEXT    NOT NULL,
    cpf         TEXT    NOT NULL UNIQUE,
    email       TEXT,
    telefone    TEXT
);


CREATE TABLE sessao (
    id_sessao       INTEGER PRIMARY KEY AUTOINCREMENT,
    id_filme        INTEGER NOT NULL,
    id_sala         INTEGER NOT NULL,
    id_funcionario  INTEGER NOT NULL,
    data_hora       TEXT    NOT NULL,   
    preco_ingresso  REAL    NOT NULL,
    FOREIGN KEY (id_filme)       REFERENCES filme(id_filme),
    FOREIGN KEY (id_sala)        REFERENCES sala(id_sala),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario),
    UNIQUE (id_sala, data_hora)
);

CREATE TABLE ingresso (
    id_ingresso     INTEGER PRIMARY KEY AUTOINCREMENT,
    id_sessao       INTEGER NOT NULL,
    id_cliente      INTEGER NOT NULL,
    assento         TEXT    NOT NULL,
    forma_pagamento TEXT    NOT NULL,
    valor_pago      REAL    NOT NULL,
    data_compra     TEXT    NOT NULL,
    FOREIGN KEY (id_sessao)  REFERENCES sessao(id_sessao),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    UNIQUE (id_sessao, assento)
);
