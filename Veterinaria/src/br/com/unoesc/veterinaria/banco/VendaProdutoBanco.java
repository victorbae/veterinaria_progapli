package br.com.unoesc.veterinaria.banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.VendaProdutoDao;
import br.com.unoesc.veterinaria.model.VendaProduto;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaVenda;

public class VendaProdutoBanco implements VendaProdutoDao {

	@Override
	public void inserir(VendaProduto dado) {
		try {
			String sql = "INSERT INTO `venda_produto`(`idVenda_Produto`, `idVenda`, `idProduto`, `Quantidade`, `Valor_Unitario`, `Valor_Total`)"
					+ " VALUES (null,null,?,?,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getProduto().getIdProduto());
			stmt.setDouble(2, dado.getQuantidade());
			stmt.setDouble(3, dado.getValorUnitario());
			stmt.setDouble(4, dado.getValorTotal());
			stmt.executeUpdate();

			// Quando o campo é auto increment no banco
			// SOMENTE QUANDO É AUTO INCEMENT NO BANCO
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			dado.setIdVendaProduto(rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(VendaProduto dado) {
		try {
			String sql = "UPDATE `venda_produto` SET `idVenda`=?,`idProduto`=?, `Quantidade`=?,`Valor_Unitario`=?,`Valor_Total`=?"
					+ " WHERE `idVenda_Produto`=?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getVenda().getIdVenda());
			stmt.setInt(2, dado.getProduto().getIdProduto());
			stmt.setDouble(3, dado.getQuantidade());
			stmt.setDouble(4, dado.getValorUnitario());
			stmt.setDouble(5, dado.getValorTotal());
			stmt.setInt(6, dado.getIdVendaProduto());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(VendaProduto dado) {
		try {
			String sql = "DELETE FROM `vendaProduto` WHERE `idVendaProduto`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdVendaProduto());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 *
	 * create or replace view lista_dados_vendaProduto as select vp.idVenda as
	 * Id_Venda, vp.idProduto as Id_Produto, vp.idVenda_Produto as Id_Venda_Produto,
	 * vp.Quantidade as Quantidade, vp.Valor_Unitario as Valor_Unitario,
	 * vp.Valor_Total as Valor_Total from Venda_Produto vp;
	 */
	@Override
	public List<VendaProduto> listar() {
		List<VendaProduto> vendaProdutos = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_vendaProduto");
			while (rs.next()) {
				VendaProduto vendaProduto = new VendaProduto();
				vendaProduto.setIdVendaProduto(rs.getInt("Id_Venda_Produto"));
				vendaProduto.setProduto(EstaticosParaVenda.achaProduto(rs.getInt("Id_Produto")));
				vendaProduto.setVenda(EstaticosParaVenda.achaVenda(rs.getInt("Id_Venda")));
				vendaProduto.setQuantidade(rs.getDouble("Quantidade"));
				vendaProduto.setValorUnitario(rs.getDouble("Valor_Unitario"));
				vendaProduto.setValorTotal(rs.getDouble("Valor_Total"));

				vendaProdutos.add(vendaProduto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendaProdutos;
	}
}
