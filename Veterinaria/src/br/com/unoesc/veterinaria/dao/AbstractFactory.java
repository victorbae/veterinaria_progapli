package br.com.unoesc.veterinaria.dao;

import br.com.unoesc.veterinaria.principal.TipoPersistencia;

public class AbstractFactory {

	private static DaoFactory factory;

	// inicializa uma unica copia da fabrica
	// pega o tipo de persistencia do Enum

	static {
		factory = TipoPersistencia.valueOf(System.getProperty("tipoPersistencia")).createFactory();
	}

	// Retorna a fabrica criada
	// @return DaoFactory
	public static DaoFactory get() {
		return factory;
	}

}
