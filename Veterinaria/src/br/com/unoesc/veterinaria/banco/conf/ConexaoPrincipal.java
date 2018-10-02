package br.com.unoesc.veterinaria.banco.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPrincipal {

	private static Connection connection = null;

	static {
		TipoBanco tipoconexao = TipoBanco.DESENVOLVIMENTO;

		if (System.getProperty("ambiente") != null) {
			tipoconexao = TipoBanco.valueOf(System.getProperty("ambiente"));
		}

		try {
			connection = DriverManager.getConnection(tipoconexao.getUrl(), tipoconexao.getUser(),
					tipoconexao.getPass());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection retornaconecao() {
		return connection;
	}
}
