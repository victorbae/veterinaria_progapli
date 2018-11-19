package br.com.unoesc.veterinaria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerErroNaoPreenchido {

	@FXML
	private Button btnContinuar;

	private Stage dialogStage;

	private boolean clicadoContinuar;

	@FXML
	void outraChance(ActionEvent event) {
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoContinuar() {
		return clicadoContinuar;
	}
}
