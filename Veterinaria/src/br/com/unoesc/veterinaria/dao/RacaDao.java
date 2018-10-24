package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Raca;

public interface RacaDao extends CrudDao<Raca> {

	List<Raca> listarNome();

}
