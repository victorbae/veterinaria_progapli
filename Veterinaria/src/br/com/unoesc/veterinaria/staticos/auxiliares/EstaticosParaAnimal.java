package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.Tipo_Animal;

public class EstaticosParaAnimal {

	public static Tipo_Animal achaTipoAnimal(Integer idTipo_Animal) {
		Tipo_Animal tipoAnimal = new Tipo_Animal();
//		Tipo_AnimalDao tipoAnimalDao = new Tipo_AnimalBanco();

		return tipoAnimal;
	}

	public static Cliente achaCliente(Integer idCliente) {
		Cliente clienteAchado = new Cliente();
		ClienteDao clienteDao = new ClienteBanco();

		for (Cliente cliente : clienteDao.listar()) {
			if (idCliente == cliente.getIdCliente()) {
				clienteAchado = cliente;
			}
		}

		return clienteAchado;
	}

	public static Raca achaRaca(Integer idRaca) {
		Raca racaAchado = new Raca();
//		RacaDao racaDao = new RacaBanco();

		return racaAchado;
	}
}
