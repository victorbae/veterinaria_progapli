package br.com.unoesc.veterinaria.model.filtros;

public class FiltrosProdutos {

	private String condicaoValor;

	private Double valorUnt;

	private String condicaoQntEstoque;

	private Double valorEst;

	public FiltrosProdutos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiltrosProdutos(String condicaoValor, Double valorUnt, String condicaoQntEstoque, Double valorEst) {
		super();
		this.condicaoValor = condicaoValor;
		this.valorUnt = valorUnt;
		this.condicaoQntEstoque = condicaoQntEstoque;
		this.valorEst = valorEst;
	}

	public String getCondicaoValor() {
		return condicaoValor;
	}

	public void setCondicaoValor(String condicaoValor) {
		this.condicaoValor = condicaoValor;
	}

	public Double getValorUnt() {
		return valorUnt;
	}

	public void setValorUnt(Double valorUnt) {
		this.valorUnt = valorUnt;
	}

	public String getCondicaoQntEstoque() {
		return condicaoQntEstoque;
	}

	public void setCondicaoQntEstoque(String condicaoQntEstoque) {
		this.condicaoQntEstoque = condicaoQntEstoque;
	}

	public Double getValorEst() {
		return valorEst;
	}

	public void setValorEst(Double valorEst) {
		this.valorEst = valorEst;
	}

}
