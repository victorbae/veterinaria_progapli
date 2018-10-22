package br.com.unoesc.veterinaria.banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Filial;

public class FilialBanco implements FilialDao {

	@Override
	public void inserir(Filial dado) {
		try {
			String sql = "INSERT INTO `veterinaria`.`filial`(`Nome`,`Endereco`,`id_Gerente`,`Telefone`,`CNPJ`)VALUES(?,?,?,?,?);";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setInt(2, dado.getGerente().getIdFuncionario());
			stmt.setString(3, dado.getTelefone());
			stmt.setString(4, dado.getTelefone());
			stmt.setString(5, dado.getCnpj());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Filial dado) {
		try {
			String sql = "UPDATE `veterinaria`.`filial` SET `Nome` = ?,`Endereco` = ?,`id_Gerente` = ?,`Telefone` = ?,`CNPJ` = ? WHERE `idFilial` = ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setInt(2, dado.getGerente().getIdFuncionario());
			stmt.setString(3, dado.getTelefone());
			stmt.setString(4, dado.getTelefone());
			stmt.setString(5, dado.getCnpj());
			stmt.setInt(6, dado.getIdFilial());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(Filial dado) {
		//
		try {
			String sql = "DELETE FROM `veterinaria`.`filial` WHERE idFilial = ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdFilial());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Filial> listar() {
		List<Filial> filiais = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_filiais");
			while (rs.next()) {
				Filial filial = new Filial();
				filial.setIdFilial(rs.getInt("Id_Filial"));
				filial.setNome(rs.getString("Nome"));
				filial.setEndereco(rs.getString("Endereco"));
				filial.setGerente(filial.buscaFuncionarioById(rs.getInt("Id_Gerente")));
				filial.setTelefone(rs.getString("Telefone"));
				filial.setCnpj(rs.getString("CNPJ"));
				filiais.add(filial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filiais;
	}

	public List<Filial> listarSemObjSecundarios() {
		List<Filial> filiais = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_filiais");
			while (rs.next()) {
				Filial filial = new Filial();
				filial.setIdFilial(rs.getInt("Id_Filial"));
				filial.setNome(rs.getString("Nome"));
				filial.setEndereco(rs.getString("Endereco"));
				// filial.setGerente(filial.buscaFuncionarioById(rs.getInt("Id_Gerente")));
				filial.setTelefone(rs.getString("Telefone"));
				filial.setCnpj(rs.getString("CNPJ"));
				filiais.add(filial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filiais;
	}

	@Override
	public List<Filial> listarNome() {
		List<Filial> filiais = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_filiais");
			while (rs.next()) {
				Filial filial = new Filial();
				filial.setIdFilial(rs.getInt("Id_Filial"));
				filial.setNome(rs.getString("Nome"));
				filiais.add(filial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filiais;
	}

}
