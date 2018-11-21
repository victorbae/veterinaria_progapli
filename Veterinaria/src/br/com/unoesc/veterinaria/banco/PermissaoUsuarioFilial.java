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
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeFuncionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaFilial;

public class PermissaoUsuarioFilial {

	public void excluir(Filial dado) {
		try {
			String sql = "DELETE FROM `veterinaria`.`funcionario_filial` WHERE idFilial = ?;";
			PreparedStatement stmt = ConexaoPrincipal.retornaconecao().prepareStatement(sql);
			stmt.setInt(1, dado.getIdFilial());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public List<Filial> listarFilial() {
		List<Filial> filiais = new ArrayList<>();
		try {
			Statement stmt = ConexaoPrincipal.retornaconecao().createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT `idFilial` FROM `veterinaria`.`funcionario_filial` where idFuncionario = "
							+ Integer.valueOf(EstaticosDeAcesso.getFuncionario().getIdFuncionario()));
			while (rs.next()) {
				filiais.add(EstaticosParaFilial.achaFilial(rs.getInt("idFilial")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filiais;
	}
}