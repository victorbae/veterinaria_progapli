package br.com.unoesc.veterinaria.dao;

public interface DaoFactory {

	VendaDao vendaDao();

	ProdutoDao produtoDao();

	ClienteDao clienteDao();

	VendaProdutoDao vendaProdutoDao();

	FilialDao filialDao();

	FuncionarioDao funcionarioDao();

	RacaDao racaDao();

	TipoAnimalDao tipoAnimalDao();

	AnimaisDao animaisDao();
}
