package br.com.unoesc.veterinaria.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import br.edu.unoesc.veterinaria.envioEmail.JavaMailApp;
import javafx.event.ActionEvent;
import java.util.Random;

public class ControllerLogin {

	@FXML
	private TextField tfSenha;

	@FXML
	private Button btnAcessar;

	@FXML
	private TextField tfLogin;

	@FXML
	private Hyperlink hyperSenha;

	Random novaSenha = new Random();

	Funcionario funcionario = new Funcionario();

	String mensagem;

	String login;

	String senha;

	@FXML
	void entrar(ActionEvent event) {
		preencheVariaveis();
		if (validaFuncionario()) {
			acessoLiberado();
		} else {

		}
	}

	@FXML
	void enviarEmail(ActionEvent event) {
		preencheVariaveis();
		funcionario.setSenha(novaSenha());
		JavaMailApp.enviar("vpsgvitor@gmail.com", novaSenha());
		System.out.println(novaSenha());
	}

	void preencheVariaveis() {
		funcionario.setEmail(tfLogin.getText());
		funcionario.setSenha(tfSenha.getText());
	}

	boolean validaFuncionario() {
		FuncionarioDao funcionarioDao = new FuncionarioBanco();
		List<Funcionario> lista = funcionarioDao.listarNome();
		for (Funcionario funcionario : lista) {
			if (funcionario.hashCodeLogin(funcionario)) {
				EstaticosDeAcesso.funcionario = this.funcionario;
				return true;
			}
		}
		return false;
	}

	void acessoLiberado() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Cliente.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			EstaticosParaGeral.bpPrincipalAux.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	String novaSenha() {
		String novasenha = "";
		for (int i = 0; i < 7; i++) {
			novasenha = novasenha + "" + String.valueOf(novaSenha.nextInt(9));
		}
		this.senha = novasenha;
		return String.valueOf(novasenha);
	}
}