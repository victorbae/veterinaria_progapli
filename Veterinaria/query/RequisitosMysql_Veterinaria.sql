
CREATE OR REPLACE VIEW lista_dados_vendaProduto AS  SELECT vp.idVenda_Produto AS  IdVenda_Produto, vp.idVenda AS  Id_Venda, vp.idProduto AS  Id_Produto, 
	vp.Quantidade AS  Quantidade, vp.Valor_Unitario AS  Valor_Unitario, vp.Valor_Total AS  Valor_Total from venda_produto vp;

CREATE OR REPLACE VIEW lista_dados_venda AS  SELECT v.idVenda AS  Id_Venda, v.Valor_Desconto AS  Valor_Desconto, v.Data_Venda AS  Data_Venda, v.idCliente AS  Id_Cliente, 
v.idFilial AS  Id_Filial, v.valorTotal AS  Valor_Total from Venda v;

CREATE OR REPLACE VIEW lista_dados_cliente AS  SELECT cl.idCliente AS  Id_Cliente, cl.Nome_Completo AS  Nome_Completo, cl.CPF AS  CPF, cl.Data_Nascimento AS  Data_Nascimento, 
cl.Endereco AS  Endereco, cl.Telefone AS  Telefone, cl.idFilial AS  Id_Filial FROM Cliente cl;

CREATE OR REPLACE VIEW view_lista_funcionarios AS  SELECT idFuncionario AS  Id_Funcionario, Nome AS  Nome, CPF AS  CPF, Data_NAS cimento, id_Cliente AS  Id_Cliente,
idFilial AS  Id_Filial, email AS  email, senha AS  senha, permissao from funcionario;

CREATE OR REPLACE VIEW view_lista_filiais AS  SELECT idFilial AS  Id_Filial, Nome, Endereco, id_Gerente AS  Id_Gerente, Telefone, CNPJ, CapacidadeEstoque AS  Capacidade_Estoque from filial;

CREATE OR REPLACE VIEW lista_dados_produto AS  SELECT pd.idProduto AS  Id_Produto, pd.Nome AS  Nome, pd.Quantidade_Estoque AS  Quantidade_Estoque, 
pd.Valor_Entrada_Unt AS  Valor_Ent_Unt, pd.Margem_Lucro AS  Margem_Lucro, pd.idFilial AS  idFilial FROM Produto pd;

CREATE OR REPLACE VIEW lista_dados_animal AS  SELECT ani.idAnimal AS  Id_Animal, ani.Nome AS  Nome, ani.Data_Nascimento AS  Data_Nascimento, ani.idTipo_Animal AS  idTipo_Animal, ani.idCliente AS  idCliente,ani.idRaca AS  idRaca FROM animal ani;

CREATE OR REPLACE VIEW lista_dados_tipo_animal AS  SELECT tp.idTipo_Animal AS  idTipoAnimal, tp.Nome AS  Nome, tp.qnt_Animais AS  Qnt_Animais FROM tipo_animal tp;

CREATE OR REPLACE VIEW lista_dados_raca AS  SELECT ra.idRaca AS  Id_Raca, ra.nome AS  Nome, ra.qnt_Animais AS  Qnt_Animais, ra.idTipoAnimal AS  idTipoAnimal FROM raca ra;


use delimiter $

/* Function calcula quantos animais tem por TipoAnimal*/
create function qtd_animais_por_tipo_animal(_id int) returns int
begin
	declare qtd_tipo int;
    select count(idAnimal) into qtd_tipo from animal where idTipo_Animal = _id;
    return qtd_tipo;
end $

/* Function calcula quantos animais tem por Raca*/
create function quantos_animais_por_raca(_id int) returns int
begin
	declare _guarda_qnt int;
    select count(idAnimal) into _guarda_qnt from animal where idRaca = _id;
    return _guarda_qnt;
end $

/* Procedure atualiza qnt_animal na tabela Tipo Animal*/
create procedure att_qtd_tipo_animal(_id int)
begin
	update tipo_Animal set qnt_Animais = qtd_animais_por_tipo_animal(_id) where idTipo_Animal = _id;
end $

/* Procedure atualiza qnt_animal na tabela Raca*/
create procedure atualiza_qnt_animal_raca(_id int)
begin
	update raca set qnt_Animais = quantos_animais_por_raca(_id) where idRaca = _id;
end $

/* Quando efetuar uma compra diminuir o estoque daquele produto*/
create procedure diminui_quantidade_comprada(_idVendaProduto int)
begin	
	declare acabou integer default 0;
	declare _idProduto bigint(20);
    declare _qnt decimal(12,2);
        
    declare listaProdutos cursor for(
		select idProduto, Quantidade from venda_produto where idVenda_Produto = _idVendaProduto);
	
   declare continue handler for not found set acabou = 1;
    
   open listaProdutos;
     repeat
			fetch listaProdutos into _idProduto, _qnt;
				if not acabou then 
					   
					update produto set Quantidade_Estoque = (Quantidade_Estoque - _qnt) where idProduto = _idProduto;
					  
				end if;      
		until acabou end repeat;
    close listaProdutos;
end $

/* Quando cancelar uma compra aumentar o estoque daquele produto*/
create procedure aumenta_quantidade_comprada(_idVendaProduto int)
begin	
	declare acabou integer default 0;
	declare _idProduto bigint(20);
    declare _qnt decimal(12,2);
        
    declare listaProdutos cursor for(
		select idProduto, Quantidade from venda_produto where idVenda_Produto = _idVendaProduto);
	    
   declare continue handler for not found set acabou = 1;
    
   open listaProdutos;
       repeat
		  fetch listaProdutos into _idProduto, _qnt;
			  if not acabou then 
			   
			  update produto set Quantidade_Estoque = (Quantidade_Estoque + _qnt) where idProduto = _idProduto;
			  
			  end if;      
		until acabou end repeat;
	close listaProdutos;
end $

/* Triggers que chamam as procedures para atualizar contadores de quantidade na raça e tipoAnimal*/
create trigger tr_atualiza_qtd_animal_raca_insert after insert on animal for each row
begin 
	call atualiza_qnt_animal_raca(new.idRaca);
	call att_qtd_tipo_animal(new.idTipo_Animal);
end $

create trigger tr_atualiza_qtd_animal_raca_delete after delete on animal for each row
begin
	call atualiza_qnt_animal_raca(old.idRaca);
	call att_qtd_tipo_animal(old.idTipo_Animal);
end $

create trigger tr_atualiza_qtd_animal_raca_update after update on animal for each row
begin
	if new.idRaca != old.idRaca then
		call atualiza_qnt_animal_raca(new.idRaca);
	end if;

	if new.idTipo_Animal != old.idTipo_Animal then
		call att_qtd_tipo_animal(new.idTipo_animal);
	end if;
end$

/* Chama a procedure que diminui quantidade de estoque*/
create trigger tr_diminui_quantidade_produtos after insert on venda_produto for each row
begin 
	call diminui_quantidade_comprada(NEW.idVenda_Produto);
end $

/* Chama a procedure que aumenta quantidade de estoque */
create trigger tr_aumenta_quantidade_produtos before delete on venda_produto for each row
begin 
	call aumenta_quantidade_comprada(OLD.idVenda_Produto);
end $

/*Trigger para venda Quando apaga uma venda apaga os produtos daquela venda*/
create trigger trg_apaga_resto_de_venda before delete on venda for each row
 begin
  
  delete from Venda_Produto where idVenda = OLD.idVenda;
    
 end $

use delimiter;