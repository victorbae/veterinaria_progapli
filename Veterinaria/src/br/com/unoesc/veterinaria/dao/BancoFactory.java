package br.com.unoesc.veterinaria.dao;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
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

	@Override
	public FilialDao filialDao() {
		return new FilialBanco();
	}

	@Override
	public FuncionarioDao funcionarioDao() {
		return new FuncionarioBanco();
	}

	@Override
	public RacaDao racaDao() {
		return new RacaBanco();
	}

	@Override
	public TipoAnimalDao tipoAnimalDao() {
		return new TipoAnimalBanco();
	}

	@Override
	public AnimaisDao animaisDao() {
		return new AnimaisBanco();
	}

}
