package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;

public class EstaticosParaAnimal {

	public static Animais animal = new Animais();
	public static boolean isEditando;

	public static TipoAnimal achaTipoAnimal(Integer idTipo_Animal) {
		TipoAnimal TipoAnimalAchado = new TipoAnimal();
		TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();

		for (TipoAnimal tipoAnimal : tipoAnimalDao.listar()) {
			if (idTipo_Animal == tipoAnimal.getIdTipoAnimal()) {
				TipoAnimalAchado = tipoAnimal;
			}
		}

		return TipoAnimalAchado;
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

	public static Raca achaRacaByNome(String nomeRaca) {
		Raca racaAchada = new Raca();
		RacaDao racaDao = new RacaBanco();

		for (Raca raca : racaDao.listar()) {
			if (nomeRaca.equals(raca.getNome())) {
				racaAchada = raca;
			}
		}
		return racaAchada;
	}

	public static TipoAnimal achaTipoAnimalByNome(String NomeCliente) {
		TipoAnimal tipoAnimalAchado = new TipoAnimal();
		TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();

		for (TipoAnimal tipoAnimal : tipoAnimalDao.listar()) {
			if (NomeCliente.equals(tipoAnimal.getNome())) {
				tipoAnimalAchado = tipoAnimal;
			}
		}
		return tipoAnimalAchado;
	}
}
