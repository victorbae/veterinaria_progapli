

create database veterinaria;
use veterinaria;

CREATE TABLE IF NOT EXISTS `Filial` (
  `idFilial` INT NOT NULL auto_increment,
  `Nome` VARCHAR(45) NULL,
  `Endereco` VARCHAR(45) NULL,
  `id_Gerente` INT NULL,
  `Telefone` VARCHAR(45) NULL,
  `CNPJ` VARCHAR(45) NULL,
  PRIMARY KEY (`idFilial`)
  );


CREATE TABLE IF NOT EXISTS `Estoque` (
  `idEstoque` INT NOT NULL auto_increment,
  `Capacidade` VARCHAR(45) NULL,
  `idFilial` INT NOT NULL,
  PRIMARY KEY (`idEstoque`),
  CONSTRAINT `fk_Estoque_Filial1`
    FOREIGN KEY (`idFilial`)
    REFERENCES `Filial` (`idFilial`)
);

CREATE TABLE IF NOT EXISTS `Produto` (
  `idProduto` INT NOT NULL auto_increment,
  `Nome` VARCHAR(45) NULL,
  `Quantidade_Estoque` VARCHAR(45) NULL,
  `Valor_Entrada_Unt` VARCHAR(45) NULL,
  `Margem_Lucro` VARCHAR(45) NULL,
  `idEstoque` INT NOT NULL,
  PRIMARY KEY (`idProduto`),
  CONSTRAINT `fk_Produto_Estoque`
    FOREIGN KEY (`idEstoque`)
    REFERENCES `Estoque` (`idEstoque`)
);


CREATE TABLE IF NOT EXISTS `Cliente` (
  `idCliente` INT NOT NULL auto_increment,
  `Nome_Completo` VARCHAR(45) NULL,
  `CPF` VARCHAR(45) NULL,
  `Data_Nascimento` VARCHAR(45) NULL,
  `Endereco` VARCHAR(45) NULL,
  `Telefone` VARCHAR(45) NULL,
  `idFilial` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  CONSTRAINT `fk_Cliente_Filial`
    FOREIGN KEY (`idFilial`)
    REFERENCES `Filial` (`idFilial`)
);

CREATE TABLE IF NOT EXISTS `Venda` (
  `idVenda` INT NOT NULL auto_increment,
  `Valor_Desconto` VARCHAR(45) NULL,
  `Data_Venda` VARCHAR(45) NULL,
  `idCliente` INT NOT NULL,
  `idFilial` INT NOT NULL,
  PRIMARY KEY (`idVenda`),
 CONSTRAINT `fk_Venda_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Cliente` (`idCliente`),
  CONSTRAINT `fk_Venda_Filial`
    FOREIGN KEY (`idFilial`)
    REFERENCES `Filial` (`idFilial`)
);

CREATE TABLE IF NOT EXISTS `Tipo_Produto` (
  `idTipo_Produto` INT NOT NULL auto_increment,
  `Nome` VARCHAR(45) NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idTipo_Produto`),
  CONSTRAINT `fk_Tipo_Produto_Produto`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Produto` (`idProduto`)
);

CREATE TABLE IF NOT EXISTS `Raca` (
  `idRaca` INT NOT NULL auto_increment,
  `Nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idRaca`)
  );

CREATE TABLE IF NOT EXISTS `Tipo_Animal` (
  `idTipo_Animal` INT NOT NULL auto_increment,
  `Nome` VARCHAR(45) NULL,
  `idRaca` INT NOT NULL,
  PRIMARY KEY (`idTipo_Animal`),
  CONSTRAINT `fk_Tipo_Animal_Raca`
    FOREIGN KEY (`idRaca`)
    REFERENCES `Raca` (`idRaca`)
);

CREATE TABLE IF NOT EXISTS `Animal` (
  `idAnimal` INT NOT NULL auto_increment,
  `Nome` VARCHAR(45) NULL,
  `Data_Nascimento` VARCHAR(45) NULL,
  `idTipo_Animal` INT NOT NULL,
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`idAnimal`),
  CONSTRAINT `fk_Animal_Tipo_Animal`
    FOREIGN KEY (`idTipo_Animal`)
    REFERENCES `Tipo_Animal` (`idTipo_Animal`),
  CONSTRAINT `fk_Animal_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Cliente` (`idCliente`)
);

CREATE TABLE IF NOT EXISTS `Funcionario` (
    `idFuncionario` INT NOT NULL auto_increment,
    `Nome` VARCHAR(45) NULL,
    `CPF` VARCHAR(45) NULL,
    `Data_Nascimento` VARCHAR(45) NULL,
    `id_Cliente` VARCHAR(45) NULL,
    `idFilial` INT NOT NULL,
    PRIMARY KEY (`idFuncionario`),
    CONSTRAINT `fk_Funcionario_Filial` FOREIGN KEY (`idFilial`)
        REFERENCES `Filial` (`idFilial`)
);

CREATE TABLE IF NOT EXISTS `Venda_Produto` (
  `idVenda` INT NOT NULL,
  `idProduto` INT NOT NULL,
  `idVenda_Produto` INT NOT NULL auto_increment,
  `Quantidade` DECIMAL(12,2) NULL,
  `Valor_Unitario` DECIMAL(12,2) NULL,
  `Valor_Total` DECIMAL (12,2) NULL,
  PRIMARY KEY (`idVenda_Produto`),
  CONSTRAINT `fk_Venda_Produto_Venda`
    FOREIGN KEY (`idVenda`)
    REFERENCES `Venda` (`idVenda`),
  CONSTRAINT `fk_Venda_Produto_Produto`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Produto` (`idProduto`)
    );

create or replace view lista_dados_vendaProduto as select vp.idVenda as Id_Venda, vp.idProduto as Id_Produto, vp.idVenda_Produto as Id_Venda_Produto,
	vp.Quantidade as Quantidade, vp.Valor_Unitario as Valor_Unitario, vp.Valor_Total as Valor_Total from Venda_Produto vp;

create or replace view lista_dados_venda as select v.idVenda as Id_Venda, v.Valor_Desconto as Valor_Desconto, v.Data_Venda as Data_Venda, v.idCliente as Id_Cliente, 
v.idFilial as Id_Filial, v.valorTotal as Valor_Total from Venda v;

CREATE OR REPLACE VIEW lista_dados_cliente AS SELECT cl.idCliente AS Id_Cliente, cl.Nome_Completo AS Nome_Completo, cl.CPF AS CPF, cl.Data_Nascimento AS Data_Nascimento, 
cl.Endereco AS Endereco, cl.Telefone AS Telefone, cl.idFilial AS Id_Filial FROM Cliente cl;

create or replace view view_lista_funcionarios as select idFuncionario as 'Id_Funcionario', Nome as 'Nome', CPF as 'CPF', Data_Nascimento, id_Cliente as 'Id_Cliente',
idFilial as 'Id_Filial'
from funcionario;

create or replace view view_lista_filiais as select idFilial as 'Id_Filial', Nome, Endereco, id_Gerente as 'Id_Gerente', Telefone, CNPJ from filial;

CREATE OR REPLACE VIEW lista_dados_produto AS SELECT pd.idProduto AS Id_Produto, pd.Nome AS Nome, pd.Quantidade_Estoque AS Qnt_Estoque, 
pd.Valor_Entrada_Unt AS Valor_Ent_Unt, pd.Margem_Lucro AS Margem_Lucro, pd.idEstoque AS Id_Estoque FROM Produto pd;

drop trigger trg_apaga_resto_de_venda;

use delimiter $ 
 create trigger trg_apaga_resto_de_venda before delete on venda for each row
 begin
  
  delete from Venda_Produto where idVenda = OLD.idVenda;
    
 end $
 use delimiter ;