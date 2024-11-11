create database hamburgueria;

use hamburgueria;

CREATE table produto (
id integer primary Key auto_increment,
logo mediumblob,
nome varchar(100) not null,
descricao varchar(300),
tipo varchar(100),
preco int not null
);

select * from produto;

CREATE TABLE usuarios (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    CPF VARCHAR(11) UNIQUE NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    num_casa INT NOT NULL,
    Cep VARCHAR(8) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    perfil ENUM('usuario', 'admin') DEFAULT 'usuario' NOT NULL
);

select * from usuarios;

UPDATE usuarios SET perfil = 'admin' WHERE email = 'admin@dominio.com';