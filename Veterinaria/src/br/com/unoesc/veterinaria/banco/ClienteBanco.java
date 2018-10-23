package br.com.unoesc.veterinaria.banco;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;

public class ClienteBanco implements ClienteDao {
	@Override
	public void inserir(Cliente dado) {
		try {
			String sql = "INSERT INTO `cliente`(`idCliente`, `Nome_Completo`, `CPF`, `Data_Nascimento`, `Endereco`, `Telefone`, `idFilial`) "
					+ " VALUES (null,?,?,?,?,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNomeCompleto());
			stmt.setString(2, dado.getCpf());
			stmt.setDate(3, (Date) dado.getDataNascimento());
			stmt.setString(4, dado.getEndereco());
			stmt.setString(5, dado.getTelefone());
			stmt.setInt(6, dado.getFilial().getIdFilial());
			stmt.executeUpdate();

			// Quando o campo é auto increment no banco
			// SOMENTE QUANDO É AUTO INCEMENT NO BANCO
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			dado.setIdCliente(rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Cliente dado) {
		try {
			String sql = "UPDATE `cliente` SET `Nome_Completo`=?,`CPF`= ?, `Data_Nascimento`= ?,`Endereco`= ?,"
					+ "`Telefone`= ?,`idFilial`= ? WHERE `idCliente`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNomeCompleto());
			stmt.setString(2, dado.getCpf());
			stmt.setDate(3, (Date) dado.getDataNascimento());
			stmt.setString(4, dado.getEndereco());
			stmt.setString(5, dado.getTelefone());
			stmt.setInt(6, dado.getFilial().getIdFilial());
			stmt.setInt(7, dado.getIdCliente());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Cliente dado) {
		try {
			String sql = "DELETE FROM `cliente` WHERE `idCliente`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdCliente());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * CREATE OR REPLACE VIEW lista_dados_cliente AS SELECT cl.idCliente AS
	 * Id_Cliente, cl.Nome_Completo AS Nome_Completo, cl.CPF AS CPF,
	 * cl.Data_Nascimento AS Data_Nascimento, cl.Endereco AS Endereco, cl.Telefone
	 * AS Telefone, cl.idFilial AS Id_Filial FROM Cliente cl;
	 */
	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_cliente");
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("Id_Cliente"));
				cliente.setNomeCompleto(rs.getString("Nome_Completo"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setDataNascimento(Date.valueOf(rs.getString("Data_Nascimento")));
				cliente.setEndereco(rs.getString("Endereco"));
				cliente.setTelefone(rs.getString("Telefone"));
				cliente.setFilial(EstaticosParaCliente.achaFilial(rs.getInt("Id_Filial")));

				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public List<Cliente> listarNome() {
		List<Cliente> clientes = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_cliente");
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("Id_Cliente"));
				cliente.setNomeCompleto(rs.getString("Nome_Completo"));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

}
