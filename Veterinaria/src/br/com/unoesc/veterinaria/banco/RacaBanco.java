package br.com.unoesc.veterinaria.banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaTipoAnimal;

public class RacaBanco implements RacaDao {

	// CREATE OR REPLACE VIEW lista_dados_raca AS SELECT r.idRaca AS idRaca, r.Nome
	// AS Nome FROM Raca r;

	@Override
	public void inserir(Raca dado) {
		try {
			String sql = "INSERT INTO `raca`(`idRaca`, `Nome`, `idTipoAnimal`) " + " VALUES (null,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, dado.getNome());
			stmt.setInt(2, dado.getTipoAnimal().getIdTipoAnimal());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			dado.setIdRaca(rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Raca dado) {
		try {
			String sql = "UPDATE `raca` SET `Nome`=?, `idTipoAnimal` = ? WHERE `idRaca`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setInt(2, dado.getTipoAnimal().getIdTipoAnimal());
			stmt.setInt(3, dado.getIdRaca());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Raca dado) {
		try {
			String sql = "DELETE FROM `raca` WHERE `idRaca`=?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdRaca());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * CREATE OR REPLACE VIEW lista_dados_raca AS SELECT ra.idRaca AS Id_Raca,
	 * ra.nome AS Nome FROM raca ra;
	 */
	@Override
	public List<Raca> listar() {
		List<Raca> racas = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_raca");
			while (rs.next()) {
				Raca raca = new Raca();
				raca.setIdRaca(rs.getInt("Id_Raca"));
				raca.setNome(rs.getString("Nome"));
				raca.setQntAnimais(rs.getInt("Qnt_Animais"));
				raca.setTipoAnimal(EstaticosParaTipoAnimal.achaTipoAnimal(rs.getInt("idTipoAnimal")));

				racas.add(raca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return racas;

	}

	@Override
	public List<Raca> findByTipoAnimal(TipoAnimal tipoAnimal) {
		List<Raca> listaRaca = new ArrayList<>();
		String sql = null;
		try {
			sql = "SELECT * FROM raca WHERE idTipoAnimal = ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);

			stmt.setInt(1, tipoAnimal.getIdTipoAnimal());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Raca raca = new Raca();
				raca.setIdRaca(rs.getInt("idRaca"));
				raca.setNome(rs.getString("Nome"));
				raca.setQntAnimais(rs.getInt("qnt_Animais"));
				raca.setTipoAnimal(EstaticosParaTipoAnimal.achaTipoAnimal(rs.getInt("idTipoAnimal")));

				listaRaca.add(raca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRaca;
	}

}
