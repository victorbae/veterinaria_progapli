package br.com.unoesc.veterinaria.controller;

import java.io.IOException;

import br.com.unoesc.veterinaria.banco.FuncionarioBanco;
import br.com.unoesc.veterinaria.dao.FuncionarioDao;
import br.com.unoesc.veterinaria.dialogs.SenhaDialogFactory;
import br.com.unoesc.veterinaria.model.Funcionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeFuncionario;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaGeral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerLogin {

	@FXML
	private PasswordField tfpSenha = new PasswordField();

	@FXML
	private Button btnAcessar;

	@FXML
	private TextField tfLogin;

	@FXML
	private Label lbSenhaIncorreta;

	@FXML
	private Hyperlink hyperSenha;

	private Funcionario funcionario = new Funcionario();
	private FuncionarioDao funcionariodao = new FuncionarioBanco();

	@FXML
	void entrar(ActionEvent event) {
		preencheVariaveis();
		if (validaLogin(this.funcionario)) {
			EstaticosDeAcesso.setLogado(true);
			EstaticosDeAcesso.setFuncionario(EstaticosDeFuncionario.getFuncionarioByLogin(this.funcionario.getEmail()));
			EstaticosDeAcesso
					.setFilial(EstaticosDeFuncionario.getFuncionarioByLogin(this.funcionario.getEmail()).getFilial());
			acessoLiberado();
		} else {
			lbSenhaIncorreta.setText("Senha ou Login incorretos");
		}
	}

	@FXML
	void enviarEmail(ActionEvent event) {
		Stage stageDono = (Stage) hyperSenha.getScene().getWindow();
		SenhaDialogFactory senhaDialog = new SenhaDialogFactory(stageDono);
		boolean clicadoSalvar = senhaDialog.showDialog();
		if (clicadoSalvar) {
		}
	}

	void preencheVariaveis() {
		funcionario.setEmail(tfLogin.getText());
		funcionario.setSenha(tfpSenha.getText());
	}

	void acessoLiberado() {
		EstaticosDeAcesso.lblNomeLogado.setText(EstaticosDeAcesso.getFuncionario().getNome());
		EstaticosDeAcesso.ftLogout.setVisible(true);
		EstaticosDeAcesso.ftUser.setVisible(true);
		EstaticosDeAcesso.verificaPermissoes();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/com/unoesc/veterinaria/fxml/Cliente.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			EstaticosParaGeral.bpPrincipalAux.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	boolean validaLogin(Funcionario funcionario) {
		for (Funcionario ff : funcionariodao.listar()) {
			if (ff.verificaExistencia(funcionario)) {
				return true;
			}
		}
		return false;
	}
}