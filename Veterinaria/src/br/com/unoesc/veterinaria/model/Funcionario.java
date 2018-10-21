package br.com.unoesc.veterinaria.model;

import java.util.Date;
import java.util.List;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.FilialDao;

public class Funcionario {
	Integer idFuncionario;
	String nome;
	String cpf;
	Date data_Nascimento;
	Cliente cliente;
	Filial filial;

	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Funcionario(Integer idFuncionario, String nome, String cpf, Date data_Nascimento, Cliente cliente,
			Filial filial) {
		super();
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.cpf = cpf;
		this.data_Nascimento = data_Nascimento;
		this.cliente = cliente;
		this.filial = filial;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getData_Nascimento() {
		return data_Nascimento;
	}

	public void setData_Nascimento(Date data_Nascimento) {
		this.data_Nascimento = data_Nascimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Cliente buscaClienteById(Integer id) {
		ClienteDao clientedao = new ClienteBanco();
		Cliente clientedoid = new Cliente();
		List<Cliente> lista = clientedao.listar();
		for (Cliente cliente : lista) {
			if (cliente.getIdCliente() == id) {
				clientedoid = cliente;
			}
		}
		return clientedoid;
	}

	public Filial buscaFilialById(Integer id) {
		Filial filialDoCliente = new Filial();
		FilialDao filialDao = new FilialBanco();
		List<Filial> lista = filialDao.listar();

		for (Filial filial : lista) {
			if (id == filial.getIdFilial()) {
				filialDoCliente = filial;
			}
		}

		return filialDoCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((data_Nascimento == null) ? 0 : data_Nascimento.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (data_Nascimento == null) {
			if (other.data_Nascimento != null)
				return false;
		} else if (!data_Nascimento.equals(other.data_Nascimento))
			return false;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (idFuncionario == null) {
			if (other.idFuncionario != null)
				return false;
		} else if (!idFuncionario.equals(other.idFuncionario))
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
