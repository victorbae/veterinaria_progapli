package br.com.unoesc.veterinaria.model;

import java.time.LocalDate;

public class Animais {

	Integer idAnimal;
	String nome;
	LocalDate data_Nascimento;
	Cliente cliente;
	TipoAnimal tipo_animal;
	Raca raca;

	public Integer getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData_Nascimento() {
		return data_Nascimento;
	}

	public void setData_Nascimento(LocalDate data_Nascimento) {
		this.data_Nascimento = data_Nascimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoAnimal getTipo_animal() {
		return tipo_animal;
	}

	public void setTipo_animal(TipoAnimal tipo_animal) {
		this.tipo_animal = tipo_animal;
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
		result = prime * result + ((data_Nascimento == null) ? 0 : data_Nascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((idAnimal == null) ? 0 : idAnimal.hashCode());
		result = prime * result + ((raca == null) ? 0 : raca.hashCode());
		result = prime * result + ((tipo_animal == null) ? 0 : tipo_animal.hashCode());
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
		if (data_Nascimento == null) {
			if (other.data_Nascimento != null)
				return false;
		} else if (!data_Nascimento.equals(other.data_Nascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
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
		if (raca == null) {
			if (other.raca != null)
				return false;
		} else if (!raca.equals(other.raca))
			return false;
		if (tipo_animal == null) {
			if (other.tipo_animal != null)
				return false;
		} else if (!tipo_animal.equals(other.tipo_animal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
