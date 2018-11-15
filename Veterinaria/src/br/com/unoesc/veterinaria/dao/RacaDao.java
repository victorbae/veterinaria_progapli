package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;

public interface RacaDao extends CrudDao<Raca> {

	List<Raca> findByTipoAnimal(TipoAnimal TipoAnimal);

}
