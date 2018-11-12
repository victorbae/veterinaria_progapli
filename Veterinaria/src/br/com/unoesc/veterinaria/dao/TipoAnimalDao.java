package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.TipoAnimal;

public interface TipoAnimalDao extends CrudDao<TipoAnimal> {

	List<TipoAnimal> listarSemObjSecundarios();

	List<TipoAnimal> listarNome();

}
