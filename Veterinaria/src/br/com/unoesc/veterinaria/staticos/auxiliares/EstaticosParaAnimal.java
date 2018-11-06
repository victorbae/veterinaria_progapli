package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.Tipo_AnimalBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.Tipo_AnimalDao;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.Tipo_Animal;

public class EstaticosParaAnimal {

	public static Animais animal = new Animais();
	public static boolean isEditando;

	public static Tipo_Animal achaTipoAnimal(Integer idTipo_Animal) {
		Tipo_Animal TipoAnimalAchado = new Tipo_Animal();
		Tipo_AnimalDao tipoAnimalDao = new Tipo_AnimalBanco();

		for (Tipo_Animal tipoAnimal : tipoAnimalDao.listar()) {
			if (idTipo_Animal == tipoAnimal.getIdTipoAnimal()) {
				TipoAnimalAchado = tipoAnimal;
			}
		}

		return TipoAnimalAchado;
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
		RacaDao racaDao = new RacaBanco();

		for (Raca raca : racaDao.listar()) {
			if (idRaca == raca.getIdRaca()) {
				racaAchado = raca;
			}
		}
		return racaAchado;
	}
}
