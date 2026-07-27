CREATE DATABASE AgroCoop;

USE AgroCoop;

CREATE TABLE produtor (
    id_produtor INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    comunidade VARCHAR(100) NOT NULL,
    propriedade VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_produtor)
);

CREATE TABLE produto_agricola (
    id_produto INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    preco_referencia DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id_produto)
);

CREATE TABLE entrega (
    id_entrega INT AUTO_INCREMENT NOT NULL,
    id_produtor INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    data_entrega DATE NOT NULL,
    tipo_precificacao ENUM('PADRAO','INSTITUCIONAL') NOT NULL,

    PRIMARY KEY (id_entrega),

    FOREIGN KEY (id_produtor)
        REFERENCES produtor(id_produtor),

    FOREIGN KEY (id_produto)
        REFERENCES produto_agricola(id_produto)
);
