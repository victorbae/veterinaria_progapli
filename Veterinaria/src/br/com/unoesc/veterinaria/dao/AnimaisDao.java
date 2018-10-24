package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Animais;

public interface AnimaisDao extends CrudDao<Animais> {

	List<Animais> listarNome();

}
