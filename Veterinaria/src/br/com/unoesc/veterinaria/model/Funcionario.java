package br.com.unoesc.veterinaria.model;

import java.time.LocalDate;
import java.util.List;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;

public class Funcionario {
	Integer idFuncionario;
	String nome;
	String cpf;
	LocalDate data_Nascimento;
	Cliente cliente;
	Filial filial;
	String senha;
	String email;

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

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Funcionario getFuncionarioByLogin(String login) {
		FuncionarioDao funcionariodao = new FuncionarioBanco();
		for (Funcionario funcionario2 : funcionariodao.listar()) {
			System.out.println(funcionario2.getEmail());
			System.out.println(login);
			if (funcionario2.equalsLogin(String.valueOf(login))) {
				return funcionario2;
			}
		}
		return null;
	}

	public Cliente buscaClienteById(Integer id) {
		ClienteDao clientedao = new ClienteBanco();
		Cliente clientedoid = new Cliente();
		List<Cliente> lista = clientedao.listarNome();
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
		List<Filial> lista = filialDao.listarNome();

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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	public boolean equalsLogin(String f) {
		if (String.valueOf(f).intern() == this.getEmail().intern()) {
			return true;
		}
		return false;
	}

	public boolean verificaExistencia(Funcionario fc) {
		if (this.email.equals(fc.getEmail()) && this.senha.equals(fc.getSenha())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return nome;
	}

}
