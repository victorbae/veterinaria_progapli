package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.filtros.FiltrosAnimais;

public interface AnimaisDao extends CrudDao<Animais> {

	List<Animais> listarNome();

	List<Animais> listarPorRacaETipoAnimal();

	List<Animais> findByFiltros(FiltrosAnimais filtroAnimal);

}
