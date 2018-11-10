package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.filtros.FiltrosVenda;

public interface VendaDao extends CrudDao<Venda> {
	List<Venda> findByFiltros(FiltrosVenda filtrosVenda);
}
