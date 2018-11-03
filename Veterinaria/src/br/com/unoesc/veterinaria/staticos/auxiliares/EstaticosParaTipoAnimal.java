package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.Tipo_Animal;

public class EstaticosParaTipoAnimal {

	public static Tipo_Animal tipo_animal = new Tipo_Animal();

	public static Raca achaRaca(Integer idRaca) {
		Raca racaAchado = new Raca();
		// RacaDao racaDao = new RacaBanco();

		return racaAchado;

	}
}
