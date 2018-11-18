package br.com.unoesc.veterinaria.staticos.auxiliares;

import java.util.List;

import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.model.Permissoes;

public class EstaticosDeFuncionario {

	public static boolean editando = false;

	public static Funcionario funcionario = new Funcionario();

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

	public static Permissoes achaPermissaoByValor(int valorPermissao) {
		if (valorPermissao == Permissoes.VENDEDOR.getPermissao()) {
			return Permissoes.VENDEDOR;
		}
		if (valorPermissao == Permissoes.SECRETARIO.getPermissao()) {
			return Permissoes.SECRETARIO;
		}
		if (valorPermissao == Permissoes.VENDEDOR_SECRETARIO.getPermissao()) {
			return Permissoes.VENDEDOR_SECRETARIO;
		}
		if (valorPermissao == Permissoes.ADMINISTRADOR.getPermissao()) {
			return Permissoes.ADMINISTRADOR;
		}
		return null;
	}

	public static Funcionario getFuncionarioByLogin(String login) {
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

}
