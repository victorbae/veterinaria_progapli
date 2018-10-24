package br.com.unoesc.veterinaria.banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.ProdutoDao;
import br.com.unoesc.veterinaria.model.Produto;

public class ProdutoBanco implements ProdutoDao {

	@Override
	public void inserir(Produto dado) {
		try {
			String sql = "INSERT INTO `produto`(`idProduto`, `Nome`, `Quantidade_Estoque`, `Valor_Entrada_Unt`, `Margem_Lucro`, `idEstoque`) "
					+ "VALUES (null,?,?,?,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setDouble(2, dado.getQuantidadeEstoque());
			stmt.setDouble(3, dado.getValorEntrada());
			stmt.setDouble(4, dado.getMargemLucro());
			// stmt.setInt(5, dado.getEstoque().getIdEstoque());
			stmt.executeUpdate();

			// Quando o campo é auto increment no banco
			// SOMENTE QUANDO É AUTO INCEMENT NO BANCO
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			dado.setIdProduto(rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Produto dado) {
		try {
			String sql = "UPDATE `produto` SET `Nome`= ?,`Quantidade_Estoque`=?,`Valor_Entrada_Unt`= ?,`Margem_Lucro`= ?,"
					+ " `idEstoque`= ? WHERE `idProduto` = ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setDouble(2, dado.getQuantidadeEstoque());
			stmt.setDouble(3, dado.getValorEntrada());
			stmt.setDouble(4, dado.getMargemLucro());
			// stmt.setInt(5, dado.getEstoque().getIdEstoque());
			stmt.setInt(6, dado.getIdProduto());

			stmt.executeUpdate();
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Produto dado) {
		try {
			String sql = "DELETE FROM `produto` WHERE `idProduto`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdProduto());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * CREATE OR REPLACE VIEW lista_dados_produto AS SELECT cl.idProduto AS
	 * Id_Produto, cl.Nome_Completo AS Nome_Completo, cl.CPF AS CPF,
	 * cl.Data_Nascimento AS Data_Nascimento, cl.Endereco AS Endereco, cl.Telefone
	 * AS Telefone, cl.idFilial AS Id_Filial FROM Produto cl;
	 */
	@Override
	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_produto");
			while (rs.next()) {

				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("Id_Produto"));
				produto.setNome(rs.getString("Nome"));
				produto.setQuantidadeEstoque(rs.getDouble("Qnt_Estoque"));
				produto.setValorEntrada(rs.getDouble("Valor_Ent_Unt"));
				produto.setMargemLucro(rs.getDouble("Margem_Lucro"));
				// produto.setEstoque(produto.achaEstoque(rs.getInt("Id_Estoque")));
				// TODO Criar metodo para buscar filial pelo codigo passado
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	@Override
	public List<Produto> listarNome() {
		// TODO Auto-generated method stub
		return null;
	}
}