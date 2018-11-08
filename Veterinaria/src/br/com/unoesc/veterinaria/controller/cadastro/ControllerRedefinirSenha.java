package br.com.unoesc.veterinaria.controller.cadastro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Random;

import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.edu.unoesc.veterinaria.envioEmail.JavaMailApp;
import javafx.event.ActionEvent;

public class ControllerRedefinirSenha {

	@FXML
	private Button btVoltar;

	@FXML
	private Button btEnviar;

	@FXML
	private Label lbvazio;

	@FXML
	private TextField tfemail;

	@FXML
	private Label lbvazio1;

	private Stage dialogStage;

	private boolean clicadoEnviar;

	Random novaSenha = new Random();

	FuncionarioDao funcionariodao = new FuncionarioBanco();

	Funcionario funcionario = new Funcionario();

	@FXML
	void enviar(ActionEvent event) {
		validarFuncionario(String.valueOf(tfemail.getText()));
		if (this.funcionario == null) {
			lbvazio.setText("e-mail não existe!");
		} else {
			funcionario.setSenha(novaSenha());
			funcionariodao.alterarSenha(this.funcionario);
			btEnviar.setDisable(true);
			btVoltar.setDisable(true);
			JavaMailApp.enviar(this.funcionario.getEmail(), this.funcionario.getSenha());
			dialogStage.close();
		}
	}

	@FXML
	void voltar(ActionEvent event) {
		dialogStage.close();
	}

	void validarFuncionario(String email) {
		this.funcionario = funcionario.getFuncionarioByLogin(email);
	}

	String novaSenha() {
		String novasenha = "";
		for (int i = 0; i < 7; i++) {
			novasenha = novasenha + "" + String.valueOf(novaSenha.nextInt(9));
		}
		return String.valueOf(novasenha);
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoEnviar() {
		return clicadoEnviar;
	}
}