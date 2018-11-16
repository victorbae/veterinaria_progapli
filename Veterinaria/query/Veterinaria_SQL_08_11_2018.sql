create schema veterinaria;
use veterinaria;

CREATE TABLE Filial (
  idFilial INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  Endereco VARCHAR(45) NULL,
  id_Gerente INT NULL,
  Telefone VARCHAR(45) NULL,
  CNPJ VARCHAR(45) NULL,
  capacidadeEstoque int
  );


CREATE TABLE Estoque (
  idEstoque INT NOT NULL auto_increment primary key,
  Capacidade VARCHAR(45) NULL,
  idFilial INT NOT NULL,
  
  CONSTRAINT fk_Estoque_Filial1 FOREIGN KEY (idFilial) REFERENCES Filial (idFilial)
);

CREATE TABLE Produto(
  idProduto INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  Quantidade_Estoque VARCHAR(45) NULL,
  Valor_Entrada_Unt VARCHAR(45) NULL,
  Margem_Lucro VARCHAR(45) NULL,
  idEstoque INT,
  
  CONSTRAINT fk_Produto_Estoque FOREIGN KEY (idEstoque) REFERENCES Estoque (idEstoque)
);


CREATE TABLE Cliente(
  idCliente INT NOT NULL auto_increment primary key,
  Nome_Completo VARCHAR(45) NULL,
  CPF VARCHAR(45) NULL,
  Data_Nascimento VARCHAR(45) NULL,
  Endereco VARCHAR(45) NULL,
  Telefone VARCHAR(45) NULL,
  idFilial INT NOT NULL,
  
  CONSTRAINT fk_Cliente_Filial FOREIGN KEY (idFilial) REFERENCES Filial (idFilial)
);

CREATE TABLE Venda(
  idVenda INT NOT NULL auto_increment primary key,
  Valor_Desconto VARCHAR(45) NULL,
  Data_Venda VARCHAR(45) NULL,
  idCliente INT NOT NULL,
  idFilial INT NOT NULL,
  valorTotal varchar(45),
  
  CONSTRAINT fk_Venda_Cliente FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente),
  
  CONSTRAINT fk_Venda_Filial FOREIGN KEY (idFilial) REFERENCES Filial (idFilial)
);

CREATE TABLE Tipo_Produto(
  idTipo_Produto INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  idProduto INT NOT NULL,

  CONSTRAINT fk_Tipo_Produto_Produto FOREIGN KEY (idProduto) REFERENCES Produto (idProduto)
);

CREATE TABLE Raca(
  idRaca INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL
  );

CREATE TABLE Tipo_Animal(
  idTipo_Animal INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  idRaca INT NOT NULL,
  
  CONSTRAINT fk_Tipo_Animal_Raca FOREIGN KEY (idRaca) REFERENCES Raca (idRaca)
);

CREATE TABLE Animal(
  idAnimal INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  Data_Nascimento VARCHAR(45) NULL,
  idTipo_Animal INT NOT NULL,
  idRaca INT NOT NULL,
  idCliente INT NOT NULL,
  
  CONSTRAINT fk_Animal_Tipo_Animal FOREIGN KEY (idTipo_Animal) REFERENCES Tipo_Animal (idTipo_Animal),
  
  CONSTRAINT fk_Animal_Raca_Animal FOREIGN KEY (idRaca) REFERENCES Raca (idRaca),
  
  CONSTRAINT fk_Animal_Cliente FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente)
);

	

CREATE TABLE Funcionario (
    idFuncionario INT NOT NULL auto_increment primary key,
    Nome VARCHAR(45) NULL,
    CPF VARCHAR(45) NULL,
    Data_Nascimento VARCHAR(45) NULL,
    id_Cliente VARCHAR(45) NULL,
    idFilial INT NOT NULL,
    email varchar(80),
    senha varchar(45),
    
    CONSTRAINT fk_Funcionario_Filial FOREIGN KEY (idFilial) REFERENCES Filial (idFilial)
);

CREATE TABLE Venda_Produto(
   idVenda_Produto INT NOT NULL auto_increment primary key,
  idVenda INT NOT NULL,
  idProduto INT NOT NULL,
  Quantidade DECIMAL(12,2) NULL,
  Valor_Unitario DECIMAL(12,2) NULL,
  Valor_Total DECIMAL (12,2) NULL,
  
  CONSTRAINT fk_Venda_Produto_Venda FOREIGN KEY (idVenda) REFERENCES Venda (idVenda),
  
  CONSTRAINT fk_Venda_Produto_Produto FOREIGN KEY (idProduto) REFERENCES Produto (idProduto)
    );
    
    
    
CREATE TABLE funcionario_filial(
	id int not null primary key auto_increment,
	idFilial int not null,
    idFuncionario int not null,
    
    foreign key(idFilial) references filial(idFilial),
    foreign key(idFuncionario) references funcionario(idFuncionario)
	);

create or replace view lista_dados_vendaProduto as select vp.idVenda as Id_Venda, vp.idProduto as Id_Produto, vp.idVenda_Produto as Id_Venda_Produto,
	vp.Quantidade as Quantidade, vp.Valor_Unitario as Valor_Unitario, vp.Valor_Total as Valor_Total from Venda_Produto vp;

create or replace view lista_dados_venda as select v.idVenda as Id_Venda, v.Valor_Desconto as Valor_Desconto, v.Data_Venda as Data_Venda, v.idCliente as Id_Cliente, 
v.idFilial as Id_Filial, v.valorTotal as Valor_Total from Venda v;

CREATE OR REPLACE VIEW lista_dados_cliente AS SELECT cl.idCliente AS Id_Cliente, cl.Nome_Completo AS Nome_Completo, cl.CPF AS CPF, cl.Data_Nascimento AS Data_Nascimento, 
cl.Endereco AS Endereco, cl.Telefone AS Telefone, cl.idFilial AS Id_Filial FROM Cliente cl;

create or replace view view_lista_funcionarios as select idFuncionario as 'Id_Funcionario', Nome as 'Nome', CPF as 'CPF', Data_Nascimento, id_Cliente as 'Id_Cliente',
idFilial as 'Id_Filial', email as 'email', senha as 'senha'
from funcionario;

create or replace view view_lista_filiais as select idFilial as 'Id_Filial', Nome, Endereco, id_Gerente as 'Id_Gerente', Telefone, CNPJ, capacidadeEstoque from filial;

CREATE OR REPLACE VIEW lista_dados_produto AS SELECT pd.idProduto AS Id_Produto, pd.Nome AS Nome, pd.Quantidade_Estoque AS Qnt_Estoque, 
pd.Valor_Entrada_Unt AS Valor_Ent_Unt, pd.Margem_Lucro AS Margem_Lucro, pd.idEstoque AS Id_Estoque FROM Produto pd;

CREATE OR REPLACE VIEW lista_dados_animal AS SELECT ani.idAnimal AS Id_Animal, ani.Nome AS Nome, ani.Data_Nascimento AS Data_Nascimento, ani.idTipo_Animal AS idTipo_Animal, ani.idCliente AS idCliente,ani.idRaca as idRaca FROM animal ani;

CREATE OR REPLACE VIEW lista_dados_tipo_animal AS SELECT tp.idTipo_Animal AS idTipoAnimal, tp.Nome AS Nome, tp.idRaca AS Id_Raca  FROM tipo_animal tp;

CREATE OR REPLACE VIEW lista_dados_raca AS SELECT ra.idRaca AS Id_Raca, ra.nome AS Nome FROM raca ra;

use delimiter $ 
 create trigger trg_apaga_resto_de_venda before delete on venda for each row
 begin
  
  delete from Venda_Produto where idVenda = OLD.idVenda;
    
 end $
 use delimiter ;
 
 insert into Filial(Nome, Endereco, Telefone, CNPJ, capacidadeEstoque)values("Filial ROOT", "Endereco ROOT", "666666666", "666666666666",666666);
 
 insert into funcionario(Nome, CPF,Data_Nascimento,idFilial,email,senha)values("Funcion�rio ROOT", "66666666666", "2018-11-08","1", "root", "root");
 
 insert into Cliente(Nome_Completo, CPF, Data_Nascimento, Endereco, Telefone, idFilial) values("Cliente ROOT", "666666666", "2018-11-08", "Endereco ROOT", "66666666", 1);
 
 update Filial set id_Gerente = 1 where idFilial = 1;
 
 INSERT INTO funcionario_filial(idFuncionario, idFilial) VALUES (1,1);
 
 
 
 

 