package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.model.Raca;

public class EstaticosParaRaca {
	public static boolean isEditando;
	public static Raca raca = new Raca();

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
}
