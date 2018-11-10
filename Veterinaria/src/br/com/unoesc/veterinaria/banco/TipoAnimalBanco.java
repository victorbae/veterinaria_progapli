package br.com.unoesc.veterinaria.banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;

public class TipoAnimalBanco implements TipoAnimalDao {

	@Override
	public void inserir(TipoAnimal dado) {
		try {
			String sql = "INSERT INTO `tipo_animal`(`idTipo_Animal`,`Nome`,`idRaca`) " + " VALUES (null,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
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
	public void alterar(TipoAnimal dado) {
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
	public void excluir(TipoAnimal dado) {
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
	public List<TipoAnimal> listar() {
		List<TipoAnimal> tipoAnimal = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_tipo_animal");
			while (rs.next()) {
				TipoAnimal tipo_Animal = new TipoAnimal();
				tipo_Animal.setIdTipoAnimal(rs.getInt("idTipoAnimal"));
				tipo_Animal.setNome(rs.getString("Nome"));
				tipo_Animal.setRaca(EstaticosParaAnimal.achaRaca(rs.getInt("Id_Raca")));

				tipoAnimal.add(tipo_Animal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoAnimal;
	}

	@Override
	public List<TipoAnimal> listarSemObjSecundarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoAnimal> listarNome() {
		List<TipoAnimal> tipoAnimal = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_tipo_animal");
			while (rs.next()) {
				TipoAnimal tipo_Animal = new TipoAnimal();
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
