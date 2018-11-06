package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;

public class EstaticosDeAcesso {

	private static Funcionario funcionario;
	private static Filial filial;
	private static boolean logado = false;

	public static Funcionario getFuncionario() {
		return funcionario;
	}

	public static void setFuncionario(Funcionario funcionario) {
		EstaticosDeAcesso.funcionario = funcionario;
	}

	public static Filial getFilial() {
		return filial;
	}

	public static void setFilial(Filial filial) {
		EstaticosDeAcesso.filial = filial;
	}

	public static boolean isLogado() {
		return logado;
	}

	public static void setLogado(boolean logado) {
		EstaticosDeAcesso.logado = logado;
	}

}
