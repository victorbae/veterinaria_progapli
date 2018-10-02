package br.com.unoesc.veterinaria.dao;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;

public class BancoFactory implements DaoFactory {

	@Override
	public VendaDao vendaDao() {
		return new VendaBanco();
	}

	@Override
	public ProdutoDao produtoDao() {
		return new ProdutoBanco();
	}

	@Override
	public ClienteDao clienteDao() {
		return new ClienteBanco();
	}

}
