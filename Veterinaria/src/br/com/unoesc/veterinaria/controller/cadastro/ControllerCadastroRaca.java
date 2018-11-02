package br.com.unoesc.veterinaria.controller.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroRaca {

	@FXML
	private TextField tfNome;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	private Stage dialogStage;
	private boolean clicadoSalvar;

	@FXML
	void Cancelar(ActionEvent event) {
		limpaTela();
		dialogStage.close();
	}

	@FXML
	void Limpar(ActionEvent event) {
		limpaTela();

	}

	@FXML
	void Salvar(ActionEvent event) {

		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	public void limpaTela() {
		tfNome.clear();

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}
