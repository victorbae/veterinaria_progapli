package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.Tipo_Animal;

public class EstaticosParaTipoAnimal {

	public static boolean isEditando;
	public static Tipo_Animal tipo_animal = new Tipo_Animal();

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
