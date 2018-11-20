package br.com.unoesc.veterinaria.controller.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ControllerNaoExcluir {

	private Stage dialogStage;
	private boolean clicadoSalvar;

	@FXML
	void btnOk(ActionEvent event) {
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}