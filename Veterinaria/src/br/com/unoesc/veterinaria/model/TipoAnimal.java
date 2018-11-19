package br.com.unoesc.veterinaria.model;

public class TipoAnimal {

	private Integer idTipoAnimal;
	private String nome;
	private Integer qntAnimais;

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
		result = prime * result + ((idTipoAnimal == null) ? 0 : idTipoAnimal.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
