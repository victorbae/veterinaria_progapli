package br.com.unoesc.veterinaria.banco;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;

public class AnimaisBanco implements AnimaisDao {

	@Override
	public void inserir(Animais dado) {
		try {
			String sql = "INSERT INTO `animal` (`idAnimal`, `Nome`, `Data_Nascimento`, `idTipo_Animal`, `idCliente`,`idRaca`)"
					+ " VALUES (null,?,?,?,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, dado.getNome());
			stmt.setDate(2, Date.valueOf(dado.getData_Nascimento()));
			stmt.setInt(3, dado.getTipo_animal().getIdTipoAnimal());
			stmt.setInt(4, dado.getCliente().getIdCliente());
			stmt.setInt(5, dado.getRaca().getIdRaca());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			dado.setIdAnimal(rs.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Animais dado) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE `animal` SET `Nome`=?, `Data_Nascimento`= ?,"
					+ "`idTipo_Animal`= ?,`idCliente`= ?,`idRaca`= ? WHERE `idAnimal`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setDate(2, Date.valueOf(dado.getData_Nascimento()));
			stmt.setInt(3, dado.getTipo_animal().getIdTipoAnimal());
			stmt.setInt(4, dado.getCliente().getIdCliente());
			stmt.setInt(5, dado.getRaca().getIdRaca());
			stmt.setInt(6, dado.getIdAnimal());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(Animais dado) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM `animal` WHERE `idAnimal`= ?";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdAnimal());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * CREATE OR REPLACE VIEW lista_dados_animal AS SELECT ani.idAnimal AS
	 * Id_Animal, ani.Nome AS Nome, ani.Data_Nascimento AS Data_Nascimento,
	 * ani.idTipo_Animal AS idTipo_Animal, ani.idCliente AS idCliente,ani.idRaca as
	 * idRaca FROM animal ani;
	 * 
	 */
	@Override
	public List<Animais> listar() {
		List<Animais> animais = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_animal");
			while (rs.next()) {
				Animais animal = new Animais();
				animal.setIdAnimal(rs.getInt("Id_Animal"));
				animal.setNome(rs.getString("Nome"));
				animal.setData_Nascimento((rs.getDate("Data_Nascimento").toLocalDate()));
				animal.setTipo_animal(EstaticosParaAnimal.achaTipoAnimal(rs.getInt("idTipo_Animal")));
				animal.setCliente(EstaticosParaAnimal.achaCliente(rs.getInt("idCliente")));
				animal.setRaca(EstaticosParaAnimal.achaRaca(rs.getInt("idRaca")));

				animais.add(animal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animais;
	}

	@Override
	public List<Animais> listarNome() {
		List<Animais> animais = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lista_dados_animal");
			while (rs.next()) {
				Animais animal = new Animais();
				animal.setIdAnimal(rs.getInt("Id_Animal"));
				animal.setNome(rs.getString("Nome"));
				animais.add(animal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animais;
	}
}