package br.com.unoesc.veterinaria.banco.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropriedadesConexao {

	private String user;
	private String passwd;
	private String url;
	private String path;

	public PropriedadesConexao(String nomeArquivo) {
		this.path = System.getProperty("user.home") + "/" + nomeArquivo;

		Properties properties = new Properties();
		try {
			FileInputStream inStream = new FileInputStream(path);

			properties.load(inStream);
			this.url = (properties.getProperty("url"));
			this.user = properties.getProperty("user");
			this.passwd = properties.getProperty("pass");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUser() {
		return user;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getPath() {
		return path;
	}

	public String getUrl() {
		return url;
	}

}
