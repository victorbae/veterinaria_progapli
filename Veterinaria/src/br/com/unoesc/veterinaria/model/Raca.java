package br.com.unoesc.veterinaria.model;

public class Raca {
	Integer idRaca;
	String nome;

	public Raca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Raca(Integer idRaca, String nome) {
		super();
		this.idRaca = idRaca;
		this.nome = nome;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRaca == null) ? 0 : idRaca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
