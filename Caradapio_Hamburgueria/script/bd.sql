CREATE table produto (
id integer primary Key auto_increment,
logo mediumblob,
nome varchar(100) not null,
descricao varchar(300),
tipo varchar(100),
preco int not null
);

select * from produto;

create table clientes(
nome varchar(100),
sobrenome varchar(100),
email varchar(100),
senha varchar(100),
telefone int,
CPF int,
endereco varchar(100),
num_casa int,
Cep int,
bairro varchar(100),
cidade varchar(100),
estado varchar(100)
);