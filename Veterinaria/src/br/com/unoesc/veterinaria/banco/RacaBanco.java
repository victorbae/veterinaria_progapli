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

public class RacaBanco implements RacaDao {

	// CREATE OR REPLACE VIEW lista_dados_raca AS SELECT r.idRaca AS idRaca, r.Nome
	// AS Nome FROM Raca r;

	@Override
	public void inserir(Raca dado) {
		try {
			String sql = "INSERT INTO `raca`(`idRaca`, `Nome`) " + " VALUES (null,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, dado.getNome());
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
			String sql = "UPDATE `raca` SET `Nome`=? WHERE `idRaca`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setInt(2, dado.getIdRaca());

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
		List<Raca> raca = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_raca");
			while (rs.next()) {
				Raca racas = new Raca();
				racas.setIdRaca(rs.getInt("idRaca"));
				racas.setNome(rs.getString("Nome"));

				raca.add(racas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return raca;

	}

	@Override
	public List<Raca> listarNome() {

		List<Raca> raca = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Nome FROM lista_dados_raca");
			while (rs.next()) {
				Raca racas = new Raca();
				racas.setIdRaca(rs.getInt("idRaca"));
				racas.setNome(rs.getString("Nome"));
				raca.add(racas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return raca;
	}

}
