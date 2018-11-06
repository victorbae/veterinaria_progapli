package br.com.unoesc.veterinaria.model;

public class Estoque {

	Integer idEstoque;
	Double capacidade;
	Filial filial;

	public Estoque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estoque(Integer idEstoque, Double capacidade, Filial filial) {
		super();
		this.idEstoque = idEstoque;
		this.capacidade = capacidade;
		this.filial = filial;
	}

	public Integer getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Integer idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capacidade == null) ? 0 : capacidade.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((idEstoque == null) ? 0 : idEstoque.hashCode());
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
		Estoque other = (Estoque) obj;
		if (capacidade == null) {
			if (other.capacidade != null)
				return false;
		} else if (!capacidade.equals(other.capacidade))
			return false;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (idEstoque == null) {
			if (other.idEstoque != null)
				return false;
		} else if (!idEstoque.equals(other.idEstoque))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estoque [idEstoque=" + idEstoque + ", capacidade=" + capacidade + ", filial=" + filial + "]";
	}

}
