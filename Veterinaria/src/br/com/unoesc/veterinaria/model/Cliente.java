package br.com.unoesc.veterinaria.model;

import java.util.Date;

import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.FilialDao;

public class Cliente {

	Integer idCliente;
	String nomeCompleto;
	String cpf;
	Date dataNascimento;
	String endereco;
	String telefone;
	Filial filial;

	public Cliente() {
		super();
	}

	public Cliente(Integer idCliente, String nomeCompleto, String cpf, Date dataNascimento, String endereco,
			String telefone) {
		super();
		this.idCliente = idCliente;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Filial achaFilial(Integer idFilial) {
		Filial filialDoCliente = new Filial();
		FilialDao filialDao = new FilialBanco();

		for (Filial filial : filialDao.listar()) {
			if (idFilial == filial.getIdFilial()) {
				filialDoCliente = filial;
			}
		}

		return filialDoCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nome:" + nomeCompleto + " CPF:" + cpf;
	}

}
