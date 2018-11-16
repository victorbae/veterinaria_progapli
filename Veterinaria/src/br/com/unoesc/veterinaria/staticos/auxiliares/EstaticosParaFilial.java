package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;

public class EstaticosParaFilial {
	public static Filial filial;
	public static boolean editando;
	public static Funcionario funcionario = new Funcionario();
	public static Cliente cliente = new Cliente();

	public static Filial achaFilial(Integer idFilial) {
		Filial filialDoCliente = new Filial();
		FilialDao filialDao = new FilialBanco();

		for (Filial filial : filialDao.listar()) {
			if (idFilial == filial.getIdFilial()) {
				filialDoCliente = filial;
			}
		}

		return filialDoCliente;
	}

}
