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
import br.com.unoesc.veterinaria.model.filtros.FiltrosProdutos;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;

public class ProdutoBanco implements ProdutoDao {

	@Override
	public void inserir(Produto dado) {
		try {
			String sql = "INSERT INTO `produto`(`idProduto`, `Nome`, `Quantidade_Estoque`, `Valor_Entrada_Unt`, `Margem_Lucro`, `idFilial`) "
					+ "VALUES (null,?,?,?,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, dado.getNome());
			stmt.setDouble(2, dado.getQuantidadeEstoque());
			stmt.setDouble(3, dado.getValorEntrada());
			stmt.setDouble(4, dado.getMargemLucro());
			stmt.setInt(5, dado.getFilial().getIdFilial());
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
			String sql = "UPDATE `produto` SET `Nome`= ?,`Quantidade_Estoque`=?,`Valor_Entrada_Unt`= ?,`Margem_Lucro`= ?, `idFilial` = ?"
					+ " WHERE `idProduto` = ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setDouble(2, dado.getQuantidadeEstoque());
			stmt.setDouble(3, dado.getValorEntrada());
			stmt.setDouble(4, dado.getMargemLucro());
			stmt.setInt(5, dado.getFilial().getIdFilial());
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
				produto.setQuantidadeEstoque(rs.getDouble("Quantidade_Estoque"));
				produto.setValorEntrada(rs.getDouble("Valor_Ent_Unt"));
				produto.setMargemLucro(rs.getDouble("Margem_Lucro"));
				produto.setFilial(EstaticosParaFilial.achaFilial(rs.getInt("idFilial")));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	@Override
	public List<Produto> findByFiltros(FiltrosProdutos filtrosProdutos) {
		List<Produto> listaProdutos = new ArrayList<>();
		String sql = null;
		PreparedStatement stmt = null;
		try {

			if (filtrosProdutos.getCondicaoQntEstoque() != null && filtrosProdutos.getValorEst() != null) {
				switch (filtrosProdutos.getCondicaoQntEstoque()) {
				case EstaticosParaGeral.MAIOR_QUE:
					sql = "SELECT * FROM produto WHERE (Quantidade_Estoque > ? or ? is null)";
					break;
				case EstaticosParaGeral.MENOR_QUE:
					sql = "SELECT * FROM produto WHERE (Quantidade_Estoque < ? or ? is null)";
					break;
				case EstaticosParaGeral.IGUAL_A:
					sql = "SELECT * FROM produto WHERE (Quantidade_Estoque = ? or ? is null)";
					break;
				}
				stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
				stmt.setDouble(1, filtrosProdutos.getValorEst() != null ? filtrosProdutos.getValorEst() : null);
				stmt.setDouble(2, filtrosProdutos.getValorEst() != null ? filtrosProdutos.getValorEst() : null);

			} else if (filtrosProdutos.getCondicaoValor() != null && filtrosProdutos.getValorUnt() != null) {
				switch (filtrosProdutos.getCondicaoValor()) {
				case EstaticosParaGeral.MAIOR_QUE:
					sql = "SELECT * FROM produto WHERE (Valor_Entrada_Unt > ? or ? is null)";
					break;
				case EstaticosParaGeral.MENOR_QUE:
					sql = "SELECT * FROM produto WHERE (Valor_Entrada_Unt < ? or ? is null)";
					break;
				case EstaticosParaGeral.IGUAL_A:
					sql = "SELECT * FROM produto WHERE (Valor_Entrada_Unt = ? or ? is null)";
					break;
				}
				stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
				stmt.setDouble(1, filtrosProdutos.getValorUnt() != null ? filtrosProdutos.getValorUnt() : null);
				stmt.setDouble(2, filtrosProdutos.getValorUnt() != null ? filtrosProdutos.getValorUnt() : null);
			} else {
				sql = "SELECT * FROM produto";
				stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("IdProduto"));
				produto.setNome(rs.getString("Nome"));
				produto.setQuantidadeEstoque(rs.getDouble("Quantidade_Estoque"));
				produto.setValorEntrada(rs.getDouble("Valor_Entrada_Unt"));
				produto.setMargemLucro(rs.getDouble("Margem_Lucro"));
				produto.setFilial(EstaticosParaFilial.achaFilial(rs.getInt("idFilial")));
				listaProdutos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProdutos;
	}
}