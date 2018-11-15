package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.model.TipoAnimal;

public class EstaticosParaTipoAnimal {

	public static boolean isEditando;
	public static TipoAnimal tipo_animal = new TipoAnimal();

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
