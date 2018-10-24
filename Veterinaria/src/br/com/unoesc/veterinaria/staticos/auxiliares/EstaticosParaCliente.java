package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Cliente;
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

	public static Cliente achaClienteByName(String NomeCliente) {
		Cliente clienteAchado = new Cliente();
		ClienteDao clienteDao = new ClienteBanco();

		for (Cliente cliente : clienteDao.listar()) {
			if (NomeCliente.equals(cliente.getNomeCompleto())) {
				clienteAchado = cliente;
			}
		}

		return clienteAchado;
	}

}
