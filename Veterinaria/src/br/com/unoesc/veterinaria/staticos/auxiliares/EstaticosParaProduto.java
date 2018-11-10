package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.banco.ProdutoBanco;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;

public class EstaticosParaProduto {

	public static boolean isEditando = false;

	public static Produto produto = new Produto();

	public static Produto achaProdutoByNome(String nomeProduto) {
		Produto produtoAchado = new Produto();
		ProdutoDao produtoDao = new ProdutoBanco();

		for (Produto produto : produtoDao.listar()) {
			if (nomeProduto.equals(produto.getNome())) {
				produtoAchado = produto;
			}
		}

		return produtoAchado;
	}

}
