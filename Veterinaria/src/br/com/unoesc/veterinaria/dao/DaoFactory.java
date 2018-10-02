package br.com.unoesc.veterinaria.dao;

public interface DaoFactory {

	VendaDao vendaDao();

	ProdutoDao produtoDao();

	ClienteDao clienteDao();
}
