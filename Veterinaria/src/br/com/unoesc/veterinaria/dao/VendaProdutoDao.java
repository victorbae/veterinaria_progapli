package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.model.VendaProduto;

public interface VendaProdutoDao extends CrudDao<VendaProduto> {

	List<VendaProduto> listarPelaVenda(Venda dado);

}
