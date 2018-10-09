package br.com.unoesc.veterinaria.model;

import java.util.Date;

public class Animais {

	Integer idAnimal;
	String Nome;
	Date Data_Nascimento;
	Cliente cliente;

	public Animais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Animais(Integer idAnimal, String nome, Date data_Nascimento) {
		super();
		this.idAnimal = idAnimal;
		Nome = nome;
		Data_Nascimento = data_Nascimento;
	}

	public Cliente getidcliente() {
		return cliente;
	}

	public Integer getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Date getData_Nascimento() {
		return Data_Nascimento;
	}

	public void setData_Nascimento(Date data_Nascimento) {
		Data_Nascimento = data_Nascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Data_Nascimento == null) ? 0 : Data_Nascimento.hashCode());
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((idAnimal == null) ? 0 : idAnimal.hashCode());
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
		Animais other = (Animais) obj;
		if (Data_Nascimento == null) {
			if (other.Data_Nascimento != null)
				return false;
		} else if (!Data_Nascimento.equals(other.Data_Nascimento))
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (idAnimal == null) {
			if (other.idAnimal != null)
				return false;
		} else if (!idAnimal.equals(other.idAnimal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animais [idAnimal=" + idAnimal + ", Nome=" + Nome + ", Data_Nascimento=" + Data_Nascimento
				+ ", cliente=" + cliente + "]";
	}

}
