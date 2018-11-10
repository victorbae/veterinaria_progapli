package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.filtros.FiltrosProdutos;

public interface ProdutoDao extends CrudDao<Produto> {

	List<Produto> listarNome();

	List<Produto> findByFiltros(FiltrosProdutos filtrosProdutos);

}
