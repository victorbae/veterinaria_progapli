package br.com.unoesc.veterinaria.dao;

import java.util.List;

import br.com.unoesc.veterinaria.model.Funcionario;

public interface FuncionarioDao extends CrudDao<Funcionario> {
	List<Funcionario> listarNome();

	void alterarSenha(Funcionario dado);
}
