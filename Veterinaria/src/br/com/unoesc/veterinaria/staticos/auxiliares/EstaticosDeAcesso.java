package br.com.unoesc.veterinaria.staticos.auxiliares;

import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.model.Permissoes;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EstaticosDeAcesso {

	private static Funcionario funcionario;
	private static Filial filial;
	private static boolean logado = false;

	public static ImageView ftLogout;
	public static ImageView ftUser;
	public static Label lblNomeLogado;

	public static Button btAnimais;

	public static Button btCliente;

	public static Button btVendas;

	public static Button btnProduto;

	public static Button btFuncionario;

	public static Button btFilial;

	public static Button btnTrocaFiliais;

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

	public static void verificaPermissoes() {
		if (EstaticosDeAcesso.getFuncionario().getPermissao() == Permissoes.SECRETARIO) {
			btAnimais.setDisable(false);
			btCliente.setDisable(false);
			btFuncionario.setDisable(false);
			btnTrocaFiliais.setDisable(false);

		}
		if (EstaticosDeAcesso.getFuncionario().getPermissao() == Permissoes.VENDEDOR) {
			btVendas.setDisable(false);
			btCliente.setDisable(false);
			btnProduto.setDisable(false);
			btnTrocaFiliais.setDisable(false);
		}
		if (EstaticosDeAcesso.getFuncionario().getPermissao() == Permissoes.VENDEDOR_SECRETARIO) {
			btVendas.setDisable(false);
			btAnimais.setDisable(false);
			btCliente.setDisable(false);
			btFuncionario.setDisable(false);
			btnProduto.setDisable(false);
			btnTrocaFiliais.setDisable(false);
		} else if (EstaticosDeAcesso.getFuncionario().getPermissao() == Permissoes.ADMINISTRADOR) {
			btVendas.setDisable(false);
			btAnimais.setDisable(false);
			btCliente.setDisable(false);
			btFuncionario.setDisable(false);
			btnProduto.setDisable(false);
			btFilial.setDisable(false);
			btnTrocaFiliais.setDisable(false);
		}
	}

	public static void bloqueiaTudo() {
		btAnimais.setDisable(true);
		btCliente.setDisable(true);
		btFuncionario.setDisable(true);
		btVendas.setDisable(true);
		btnProduto.setDisable(true);
		btFilial.setDisable(true);
		btnTrocaFiliais.setDisable(true);
	}

}
