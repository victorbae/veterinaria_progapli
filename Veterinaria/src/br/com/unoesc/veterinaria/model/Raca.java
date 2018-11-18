package br.com.unoesc.veterinaria.model;

public class Raca {

	private Integer idRaca;
	private String nome;
	private TipoAnimal tipoAnimal;
	private Integer qntAnimais;

	public Integer getIdRaca() {
		return idRaca;
	}

	public void setIdRaca(Integer idRaca) {
		this.idRaca = idRaca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public Integer getQntAnimais() {
		return qntAnimais;
	}

	public void setQntAnimais(Integer qntAnimais) {
		this.qntAnimais = qntAnimais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRaca == null) ? 0 : idRaca.hashCode());
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
		Raca other = (Raca) obj;
		if (idRaca == null) {
			if (other.idRaca != null)
				return false;
		} else if (!idRaca.equals(other.idRaca))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
