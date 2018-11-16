package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;

public interface ClienteDao extends CrudDao<Cliente> {

	List<Cliente> listarNome();
	List<Cliente> listarSomenteParaFilialLogada();

}
