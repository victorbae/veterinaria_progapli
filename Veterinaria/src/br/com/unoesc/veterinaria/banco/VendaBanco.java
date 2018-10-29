package br.com.unoesc.veterinaria.banco;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.VendaDao;
import br.com.unoesc.veterinaria.model.Venda;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;

public class VendaBanco implements VendaDao {

	@Override
	public void inserir(Venda dado) {
		try {
			String sql = "INSERT INTO `venda`(`idVenda`, `Valor_Desconto`, `Data_Venda`, `idCliente`, `idFilial`, `valorTotal`) "
					+ "VALUES (null,?,?,?,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, dado.getValorDesconto());
			stmt.setDate(2, Date.valueOf(dado.getDataVenda()));
			stmt.setInt(3, dado.getCliente().getIdCliente());
			stmt.setInt(4, dado.getFilial().getIdFilial());
			stmt.setDouble(5, dado.getValorTotal());
			stmt.executeUpdate();

			// Quando o campo é auto increment no banco
			// SOMENTE QUANDO É AUTO INCEMENT NO BANCO
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			dado.setIdVenda(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Venda dado) {
		try {
			String sql = "UPDATE `venda` SET `Valor_Desconto`=?,`Data_Venda`=?,`idCliente`=?,`idFilial`=?,`valorTotal`=? WHERE `idVenda`=?,";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setDouble(1, dado.getValorDesconto());
			stmt.setDate(2, Date.valueOf(dado.getDataVenda()));
			stmt.setInt(3, dado.getCliente().getIdCliente());
			stmt.setInt(4, dado.getFilial().getIdFilial());
			stmt.setDouble(5, dado.getValorTotal());
			stmt.setInt(6, dado.getIdVenda());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Venda dado) {
		try {
			String sql = "DELETE FROM `venda` WHERE `idVenda`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdVenda());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * create or replace view lista_dados_venda as select v.idVenda as Id_Venda,
	 * v.Valor_Desconto as Valor_Desconto, v.Data_Venda as Data_Venda, v.idCliente
	 * as Id_Cliente, v.idFilial as Id_Filial, v.valorTotal as Valor_Total from
	 * Venda v;
	 */
	@Override
	public List<Venda> listar() {
		List<Venda> vendas = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_venda");
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt("Id_Venda"));
				venda.setValorDesconto(rs.getDouble("Valor_Desconto"));
				venda.setDataVenda(LocalDate.parse(rs.getString("Data_Venda")));
				venda.setValorTotal(rs.getDouble("Valor_Total"));
				venda.setCliente(EstaticosParaCliente.achaCliente(rs.getInt("Id_Cliente")));
				venda.setFilial(EstaticosParaCliente.achaFilial(rs.getInt("Id_Filial")));

				vendas.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendas;
	}
}