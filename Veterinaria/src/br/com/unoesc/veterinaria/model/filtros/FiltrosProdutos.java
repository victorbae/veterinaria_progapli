package br.com.unoesc.veterinaria.model.filtros;

public class FiltrosProdutos {

	private String condicaoQntEstoque;

	private String condicaoValor;

	public FiltrosProdutos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiltrosProdutos(String condicaoQntEstoque, String condicaoValor) {
		super();
		this.condicaoQntEstoque = condicaoQntEstoque;
		this.condicaoValor = condicaoValor;
	}

	public String getCondicaoQntEstoque() {
		return condicaoQntEstoque;
	}

	public void setCondicaoQntEstoque(String condicaoQntEstoque) {
		this.condicaoQntEstoque = condicaoQntEstoque;
	}

	public String getCondicaoValor() {
		return condicaoValor;
	}

	public void setCondicaoValor(String condicaoValor) {
		this.condicaoValor = condicaoValor;
	}

}
