create schema veterinaria;
use veterinaria;

CREATE TABLE Filial (
  idFilial INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  Endereco VARCHAR(45) NULL,
  id_Gerente INT NULL,
  Telefone VARCHAR(45) NULL,
  CNPJ VARCHAR(45) NULL,
  CapacidadeEstoque DECIMAL(12,2) NOT NULL
  );

CREATE TABLE Produto(
  idProduto INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  Quantidade_Estoque VARCHAR(45) NULL,
  Valor_Entrada_Unt VARCHAR(45) NULL,
  Margem_Lucro VARCHAR(45) NULL,
  idFilial INT NOT NULL,
  
  CONSTRAINT fk_Produto_Filial FOREIGN KEY (idFilial) REFERENCES Filial (idFilial)
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

CREATE TABLE Tipo_Animal(
  idTipo_Animal INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  qnt_Animais INT NULL

);

CREATE TABLE Raca(
  idRaca INT NOT NULL auto_increment primary key,
  Nome VARCHAR(45) NULL,
  idTipoAnimal INT NOT NULL,
  qnt_Animais INT NULL,
  
  CONSTRAINT fk_Tipo_Animal_Raca FOREIGN KEY (idTipoAnimal) REFERENCES Tipo_Animal (idTipo_Animal)
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
    permissao INT NOT NULL,
    
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

/* inserts Básicos para funcionar bala*/
 

 
 insert into Cliente(Nome_Completo, CPF, Data_Nascimento, Endereco, Telefone, idFilial) values("Cliente ROOT", "666666", "2018-11-08", "Endereco ROOT", "666666", 1);
 
 update Filial set id_Gerente = 1 where idFilial = 1;
 
 INSERT INTO `filial` (`idFilial`, `Nome`, `Endereco`, `id_Gerente`, `Telefone`, `CNPJ`, `CapacidadeEstoque`) VALUES
(1, 'Filial ROOT', 'Endereco ROOT', NULL, '666666', '666666', '666666.00');

 /* Insere Funcionarios com Todas as permissoes */
INSERT INTO `funcionario` (`idFuncionario`, `Nome`, `CPF`, `Data_Nascimento`, `id_Cliente`, `idFilial`, `email`, `senha`, `permissao`) VALUES
(1, 'Funcionário ROOT', '666666', '2018-11-08', NULL, 1, 'root', 'root', 9),
(2, 'Secretario', '897456213', '1998-04-26', NULL, 1, 'sec', 'root', 3),
(3, 'Vendedor', '78945612', '1997-06-05', NULL, 1, 'vend', 'root', 2),
(4, 'Vendedor Secretario', '185962', '1996-05-15', NULL, 1, 'secvend', 'root', 6);

INSERT INTO `cliente` (`idCliente`, `Nome_Completo`, `CPF`, `Data_Nascimento`, `Endereco`, `Telefone`, `idFilial`) VALUES
(1, 'Cliente ROOT', '666666', '2018-11-08', 'Endereco ROOT', '666666', 1),
(2, 'Joao ', '123456489', '2018-11-15', 'Rua das Briga de Faca', '49 9 99173453', 1);

INSERT INTO `tipo_animal` (`idTipo_Animal`, `Nome`, `qnt_Animais`) VALUES
(1, 'Cavalo', 3),
(2, 'Passaro', 2),
(4, 'Cachorro ssss', 3),
(6, 'Boi', NULL);

INSERT INTO `raca` (`idRaca`, `Nome`, `qnt_Animais`, `idTipoAnimal`) VALUES
(3, 'Pastor Alemao', 1, 4),
(4, 'Pit Bull', 2, 4),
(5, 'Pardal', 2, 2),
(7, 'Quarto de Milha', 1, 1),
(8, 'Manga Larga', 2, 1);

 INSERT INTO `animal` (`idAnimal`, `Nome`, `Data_Nascimento`, `idTipo_Animal`, `idRaca`, `idCliente`) VALUES
(1, 'Brahma', '2018-01-28', 4, 3, 2),
(2, 'Bixo Brabo', '2018-11-15', 4, 4, 2),
(4, 'Pé de Pano ss', '2018-11-14', 1, 8, 2),
(5, 'Pintinho ss', '2018-10-31', 2, 5, 1),
(12, 'The Cao', '2018-11-11', 4, 4, 2),
(13, 'Cavalo Do ', '2018-11-18', 1, 7, 2),
(14, 'Outro Pardal', '2018-11-05', 2, 5, 2),
(15, 'Outro Cavalo', '2018-11-01', 1, 8, 2);

INSERT INTO `produto` (`idProduto`, `Nome`, `Quantidade_Estoque`, `Valor_Entrada_Unt`, `Margem_Lucro`, `idFilial`) VALUES
(1, 'MataCura', '676', '25.0', '2.0', 1),
(2, 'Gelol de Cavalo', '317', '18.0', '2.0', 1);





