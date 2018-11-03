package br.com.unoesc.veterinaria.banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.Tipo_AnimalDao;
import br.com.unoesc.veterinaria.model.Tipo_Animal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaTipoAnimal;

public class Tipo_AnimalBanco implements Tipo_AnimalDao {

	@Override
	public void inserir(Tipo_Animal dado) {
		try {
			String sql = "INSERT INTO `tipo_animal`(`idTipo_Animal`, `Nome`,`idRaca`) " + " VALUES (null,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setInt(2, dado.getRaca().getIdRaca());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			dado.setIdTipoAnimal(rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Tipo_Animal dado) {
		try {
			String sql = "UPDATE `tipo_animal` SET `Nome`=?,`idRaca`= ? WHERE `idTipo_Animal`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setInt(2, dado.getRaca().getIdRaca());
			stmt.setInt(3, dado.getIdTipoAnimal());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(Tipo_Animal dado) {
		try {
			String sql = "DELETE FROM `tipo_animal` WHERE `idTipo_Animal`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdTipoAnimal());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Tipo_Animal> listar() {
		List<Tipo_Animal> tipoAnimal = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_tipo_animal");
			while (rs.next()) {
				Tipo_Animal tipo_Animal = new Tipo_Animal();
				tipo_Animal.setIdTipoAnimal(rs.getInt("idTipoAnimal"));
				tipo_Animal.setNome(rs.getString("Nome"));
				tipo_Animal.setRaca(EstaticosParaTipoAnimal.achaRaca(rs.getInt("idRaca")));

				tipoAnimal.add(tipo_Animal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoAnimal;
	}

	@Override
	public List<Tipo_Animal> listarSemObjSecundarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tipo_Animal> listarNome() {
		List<Tipo_Animal> tipoAnimal = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_tipo_animal");
			while (rs.next()) {
				Tipo_Animal tipo_Animal = new Tipo_Animal();
				tipo_Animal.setIdTipoAnimal(rs.getInt("idTipoAnimal"));
				tipo_Animal.setNome(rs.getString("Nome"));

				tipoAnimal.add(tipo_Animal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoAnimal;

	}
}
