package br.com.unoesc.veterinaria.model;

public class TipoAnimal {
	Integer idTipoAnimal;
	String nome;
	Raca raca;

	public TipoAnimal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoAnimal(Integer idTipoAnimal, String nome, Raca raca) {
		super();
		this.idTipoAnimal = idTipoAnimal;
		this.nome = nome;
		this.raca = raca;
	}

	public Integer getIdTipoAnimal() {
		return idTipoAnimal;
	}

	public void setIdTipoAnimal(Integer idTipoAnimal) {
		this.idTipoAnimal = idTipoAnimal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoAnimal == null) ? 0 : idTipoAnimal.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((raca == null) ? 0 : raca.hashCode());
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
		TipoAnimal other = (TipoAnimal) obj;
		if (idTipoAnimal == null) {
			if (other.idTipoAnimal != null)
				return false;
		} else if (!idTipoAnimal.equals(other.idTipoAnimal))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (raca == null) {
			if (other.raca != null)
				return false;
		} else if (!raca.equals(other.raca))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
