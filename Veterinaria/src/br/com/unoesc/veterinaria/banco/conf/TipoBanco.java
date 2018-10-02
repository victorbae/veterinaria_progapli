package br.com.unoesc.veterinaria.banco.conf;

public enum TipoBanco {

	DESENVOLVIMENTO(new PropriedadesConexao("desenvolvimento.properties")),
	CLIENTE(new PropriedadesConexao("cliente.properties"));

	TipoBanco(PropriedadesConexao propiedadesconexao) {
		this.propiedadesconexao = propiedadesconexao;
	}

	private PropriedadesConexao propiedadesconexao;

	public String getUser() {
		return propiedadesconexao.getUser();
	}

	public String getPass() {
		return propiedadesconexao.getPasswd();
	}

	public String getUrl() {
		return propiedadesconexao.getUrl();
	}
}
