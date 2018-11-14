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
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeFuncionario;

public class FilialBanco implements FilialDao {

	@Override
	public void inserir(Filial dado) {
		try {
			String sql = "INSERT INTO `veterinaria`.`filial`(`Nome`,`Endereco`,`id_Gerente`,`Telefone`,`CNPJ`,`capacidadeEstoque`)VALUES(?,?,?,?,?,?);";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setString(2, dado.getEndereco());
			stmt.setInt(3, dado.getGerente().getIdFuncionario());
			stmt.setString(4, dado.getTelefone());
			stmt.setString(5, dado.getCnpj());
			stmt.setInt(6, dado.getCapacidadeEstoque());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Filial dado) {
		try {
			String sql = "UPDATE `veterinaria`.`filial` SET `Nome` = ?,`Endereco` = ?,`id_Gerente` = ?,`Telefone` = ?,`CNPJ` = ?, `capacidadeEstoque` = ? WHERE `idFilial` = ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setString(2, dado.getEndereco());
			stmt.setInt(3, dado.getGerente().getIdFuncionario());
			stmt.setString(4, dado.getTelefone());
			stmt.setString(5, dado.getCnpj());
			stmt.setInt(6, dado.getCapacidadeEstoque());
			stmt.setInt(7, dado.getIdFilial());
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
				filial.setGerente(EstaticosDeFuncionario.buscaFuncionarioById(rs.getInt("Id_Gerente")));
				filial.setTelefone(rs.getString("Telefone"));
				filial.setCnpj(rs.getString("CNPJ"));
				filial.setCapacidadeEstoque(rs.getInt("capacidadeEstoque"));
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
				filial.setGerente(EstaticosDeFuncionario.buscaFuncionarioById(rs.getInt("Id_Gerente")));
				filial.setTelefone(rs.getString("Telefone"));
				filial.setCnpj(rs.getString("CNPJ"));
				filial.setCapacidadeEstoque(rs.getInt("capacidadeEstoque"));
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
