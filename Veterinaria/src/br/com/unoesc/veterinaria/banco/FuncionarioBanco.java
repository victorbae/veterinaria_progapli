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
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;

public class FuncionarioBanco implements FuncionarioDao {

	@Override
	public void inserir(Funcionario dado) {
		// PermissoesBanco permissoes = new PermissoesBanco();
		try {
			String sql = "INSERT INTO `veterinaria`.`funcionario`(`Nome`,`CPF`,`Data_Nascimento`,`id_Cliente`,`idFilial`,`email`,`senha`) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, String.valueOf(dado.getNome()));
			stmt.setString(2, dado.getCpf());
			stmt.setString(3, dado.getData_Nascimento().toString());
			stmt.setInt(4, dado.getCliente().getIdCliente());
			stmt.setInt(5, dado.getFilial().getIdFilial());
			stmt.setString(6, dado.getEmail());
			stmt.setString(7, dado.getSenha());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			EstaticosParaFilial.funcionario = dado;
			EstaticosParaFilial.funcionario.setIdFuncionario(rs.getInt(1));

//			permissoes.inserirNovaPermissaoDeFilial(EstaticosParaFilial.funcionario.getFilial(),
//					EstaticosParaFilial.funcionario);

			EstaticosParaFilial.cliente = dado.getCliente();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Funcionario dado) {
		try {
			String sql = "UPDATE `veterinaria`.`funcionario` SET `Nome` = ?,`CPF` = ?,`Data_Nascimento` = ?,`id_Cliente` = ?,`idFilial` = ?, `senha` = ?, `email` = ? WHERE `idFuncionario` = ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setString(2, dado.getCpf());
			stmt.setDate(3, Date.valueOf(dado.getData_Nascimento()));
			stmt.setInt(4, dado.getCliente().getIdCliente());
			stmt.setInt(5, dado.getFilial().getIdFilial());
			stmt.setString(6, dado.getSenha());
			stmt.setString(7, dado.getEmail());
			stmt.setInt(8, dado.getIdFuncionario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Funcionario dado) {
		try {
			String sql = "DELETE FROM `veterinaria`.`funcionario` WHERE idFuncionario = ?;";
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
				funcionario.setData_Nascimento(LocalDate.parse(rs.getString("Data_Nascimento")));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setCliente(funcionario.buscaClienteById(rs.getInt("Id_Cliente")));
				funcionario.setFilial(funcionario.buscaFilialById(rs.getInt("Id_Filial")));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

	public List<Funcionario> listarSemObjSecundarios() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_funcionarios");
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getInt("Id_Funcionario"));
				funcionario.setNome(rs.getString("Nome"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setData_Nascimento(rs.getDate("Data_Nascimento").toLocalDate());
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

	@Override
	public void alterarSenha(Funcionario dado) {
		try {
			String sql = "UPDATE `veterinaria`.`funcionario` SET `senha` = ? WHERE `idFuncionario` = ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getSenha());
			stmt.setInt(2, dado.getIdFuncionario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Funcionario> listarSomenteParaFilialLogada() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM view_lista_funcionarios where Id_Filial = "
					+ EstaticosDeAcesso.getFilial().getIdFilial());
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setIdFuncionario(rs.getInt("Id_Funcionario"));
				funcionario.setNome(rs.getString("Nome"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setData_Nascimento(LocalDate.parse(rs.getString("Data_Nascimento")));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setCliente(funcionario.buscaClienteById(rs.getInt("Id_Cliente")));
				funcionario.setFilial(funcionario.buscaFilialById(rs.getInt("Id_Filial")));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}

}
