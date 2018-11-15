package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.model.Cliente;

public class EstaticosParaCliente {

	public static boolean isEditando;

	public static Cliente cliente = new Cliente();

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
