package br.com.unoesc.veterinaria.dao;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.banco.VendaProdutoBanco;

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

	@Override
	public VendaProdutoDao vendaProdutoDao() {
		return new VendaProdutoBanco();
	}

}
