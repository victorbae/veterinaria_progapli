package br.com.unoesc.veterinaria.staticos.auxiliares;

import java.util.List;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.banco.VendaProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.dao.VendaProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.VendaProduto;

public class EstaticosParaVenda {

	public static VendaProduto vendaProduto;
	public static Venda venda;
	public static VendaProdutoDao vendaProdutoDao = new VendaProdutoBanco();
	public static List<VendaProduto> carrinhoAux;

	public static Produto achaProduto(Integer idProduto) {

		Produto produtoDaVendaProduto = new Produto();
		ProdutoDao produtoDao = new ProdutoBanco();

		for (Produto produto : produtoDao.listar()) {
			if (idProduto == produto.getIdProduto()) {
				produtoDaVendaProduto = produto;
			}
		}

		return produtoDaVendaProduto;
	}

	public static Venda achaVenda(Integer idVenda) {
		Venda vendaDaVendaProduto = new Venda();
		VendaDao vendaDao = new VendaBanco();

		for (Venda venda : vendaDao.listar()) {
			if (idVenda == venda.getIdVenda()) {
				vendaDaVendaProduto = venda;
			}
		}

		return vendaDaVendaProduto;
	}

	public static VendaProduto achaVendaProduto(Integer idVendaProduto) {
		VendaProduto vendaProdutoDaVenda = new VendaProduto();
		VendaProdutoDao vendaProdutoDao = new VendaProdutoBanco();

		for (VendaProduto vendaProduto : vendaProdutoDao.listar()) {
			if (idVendaProduto == vendaProduto.getIdVendaProduto()) {
				vendaProdutoDaVenda = vendaProduto;
			}
		}

		return vendaProdutoDaVenda;
	}

}
