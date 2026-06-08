CREATE TABLE pet (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raca VARCHAR(50),
    idade INTEGER,
    descricao TEXT,
    status VARCHAR(20) NOT NULL
);