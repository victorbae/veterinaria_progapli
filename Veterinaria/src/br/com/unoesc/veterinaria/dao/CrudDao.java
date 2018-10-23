package br.com.unoesc.veterinaria.dao;

import java.util.List;

public interface CrudDao<T> {

	void inserir(T dado);

	void alterar(T dado);

	void excluir(T dado);

	List<T> listar();

}
