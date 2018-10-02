package br.com.unoesc.veterinaria.model;

public class Produto {

	Integer idProduto;
	String nome;
	Double quantidadeEstoque;
	Double valorEntrada;
	Double margemLucro;
//	Estoque estoque;

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(Integer idProduto, String nome, Double quantidadeEstoque, Double valorEntrada, Double margemLucro) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.quantidadeEstoque = quantidadeEstoque;
		this.valorEntrada = valorEntrada;
		this.margemLucro = margemLucro;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public Double getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(Double margemLucro) {
		this.margemLucro = margemLucro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + " Quantidade Estoque: " + quantidadeEstoque;
	}

}
