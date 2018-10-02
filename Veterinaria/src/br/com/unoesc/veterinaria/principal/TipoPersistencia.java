package br.com.unoesc.veterinaria.principal;

import br.com.unoesc.veterinaria.dao.BancoFactory;
import br.com.unoesc.veterinaria.dao.DaoFactory;

public enum TipoPersistencia {

	BANCO(new BancoFactory());

	TipoPersistencia(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	private DaoFactory daoFactory;

	public DaoFactory createFactory() {
		return daoFactory;
	}
}
