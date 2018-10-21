package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.banco.VendaBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.model.Produto;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.VendaProduto;

public class EstaticosParaVenda {

	public static VendaProduto vendaProduto;

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
}
