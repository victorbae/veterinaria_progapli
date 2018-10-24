package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Produto;

public interface ProdutoDao extends CrudDao<Produto> {

	List<Produto> listarNome();

}
