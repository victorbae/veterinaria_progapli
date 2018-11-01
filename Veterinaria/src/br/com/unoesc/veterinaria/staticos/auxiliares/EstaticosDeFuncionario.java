package br.com.unoesc.veterinaria.staticos.auxiliares;

import java.util.List;

import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.model.Funcionario;

public class EstaticosDeFuncionario {
	
	public static Funcionario buscaFuncionarioById(Integer id) {
		FuncionarioDao funcionarioDao = new FuncionarioBanco();
		Funcionario funcionariodoid = new Funcionario();
		List<Funcionario> lista = funcionarioDao.listarNome();
		for (Funcionario funcionario : lista) {
			if (funcionario.getIdFuncionario() == id) {
				funcionariodoid = funcionario;
			}
		}
		return funcionariodoid;
	}

}
