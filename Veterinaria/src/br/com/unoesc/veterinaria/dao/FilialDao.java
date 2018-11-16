package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Filial;

public interface FilialDao extends CrudDao<Filial> {

	List<Filial> listarNome();
	List<Filial> listarSomenteParaFilialLogada();

}
