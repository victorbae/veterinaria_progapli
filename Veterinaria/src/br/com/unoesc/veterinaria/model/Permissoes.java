package br.com.unoesc.veterinaria.model;

public enum Permissoes {
	ADMINISTRADOR(9), VENDEDOR_SECRETARIO(6), SECRETARIO(3), VENDEDOR(2);

	private Integer permissao;

	private Permissoes(Integer permissao) {
		this.permissao = permissao;
	}

	public Integer getPermissao() {
		return permissao;
	}

	public void setPermissao(Integer permissao) {
		this.permissao = permissao;
	}

}
