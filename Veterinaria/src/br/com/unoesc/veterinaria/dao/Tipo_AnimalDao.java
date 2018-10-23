package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Tipo_Animal;

public interface Tipo_AnimalDao extends CrudDao<Tipo_Animal> {

	List<Tipo_Animal> listarSemObjSecundarios();

	List<Tipo_Animal> listarNome();

}
