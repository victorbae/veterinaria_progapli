package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Filial;

public class EstaticosParaCliente {

	public static Filial achaFilial(Integer idFilial) {
		Filial filialDoCliente = new Filial();
		FilialDao filialDao = new FilialBanco();

//		for (Filial filial : filialDao.listar()) {
//			if (idFilial == filial.getIdFilial()) {
//				filialDoCliente = filial;
//			}
//		}

		return filialDoCliente;
	}

}
