package br.com.unoesc.veterinaria.banco;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.unoesc.veterinaria.banco.conf.ConexaoPrincipal;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.model.Animais;

public class AnimaisBanco implements AnimaisDao {

	@Override
	public void inserir(Animais dado) {
		try {
			String sql = "INSERT INTO `animal`(`idAnimal`, `Nome`, `Data_Nascimento`, `idTipo_Animal`, `idCliente`) "
					+ " VALUES (null,?,?,?,?)";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setString(1, dado.getNome());
			stmt.setDate(2, (Date) dado.getData_Nascimento());
			stmt.setInt(3, dado.getTipo_animal().getIdTipoAnimal());
			stmt.setInt(4, dado.getCliente().getIdCliente());
			stmt.executeUpdate();

			// Quando o campo é auto increment no banco
			// SOMENTE QUANDO É AUTO INCEMENT NO BANCO
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

	}

	@Override
	public void excluir(Animais dado) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Animais> listar() {
		// TODO Auto-generated method stub
		return null;
	}
}