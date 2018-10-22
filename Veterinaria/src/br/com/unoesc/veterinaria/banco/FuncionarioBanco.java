package br.com.unoesc.veterinaria.banco;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;

public class FuncionarioBanco implements FuncionarioDao {

	@Override
	public void inserir(Funcionario dado) {
		try {
			String sql = "INSERT INTO `veterinaria`.`funcionario`(`Nome`,`CPF`,`Data_Nascimento`,`id_Cliente`,`idFilial`) VALUES (?,?,?,?,?);";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setString(2, dado.getCpf());
			stmt.setDate(3, (Date) dado.getData_Nascimento());
			stmt.setInt(4, dado.getCliente().getIdCliente());
			stmt.setInt(5, dado.getFilial().getIdFilial());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Funcionario dado) {
		try {
			String sql = "UPDATE `veterinaria`.`funcionario` SET `Nome` = ?,`CPF` = ?,`Data_Nascimento` = ?,`id_Cliente` = ?,`idFilial` = ? WHERE `idFuncionario` = ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setString(2, dado.getCpf());
			stmt.setDate(3, (Date) dado.getData_Nascimento());
			stmt.setInt(4, dado.getCliente().getIdCliente());
			stmt.setInt(5, dado.getFilial().getIdFilial());
			stmt.setInt(6, dado.getIdFuncionario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Funcionario dado) {
		try {
			String sql = "DELETE FROM `veterinaria`.`funcionario` WHERE ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdFuncionario());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Funcionario> listar() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_funcionarios");
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getInt("Id_Funcionario"));
				funcionario.setNome(rs.getString("Nome"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setData_Nascimento(rs.getDate("Data_Nascimento"));
				funcionario.setCliente(funcionario.buscaClienteById(rs.getInt("Id_Cliente")));
				funcionario.setFilial(funcionario.buscaFilialById(rs.getInt("Id_Filial")));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}
	
	public List<Funcionario> listarSemObjSecundarios(){
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_funcionarios");
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getInt("Id_Funcionario"));
				funcionario.setNome(rs.getString("Nome"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setData_Nascimento(rs.getDate("Data_Nascimento"));
				funcionario.setCliente(funcionario.buscaClienteById(rs.getInt("Id_Cliente")));
				funcionario.setFilial(funcionario.buscaFilialById(rs.getInt("Id_Filial")));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

	@Override
	public List<Funcionario> listarNome() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_funcionarios");
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getInt("Id_Funcionario"));
				funcionario.setNome(rs.getString("Nome"));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

}
