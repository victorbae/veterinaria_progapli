package br.com.unoesc.veterinaria.controller;

import br.com.unoesc.veterinaria.banco.PermissoesUsuarioFilial;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ControllerTrocaFIliais {

	@FXML
	private ComboBox<Filial> cbxFiliaisPermitidas;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	@FXML
	void initialize() {
		preencheCombo();
	}

	@FXML
	void btCancelar(ActionEvent event) {
		if (dialogStage != null) {
			dialogStage.close();
		}

	}

	@FXML
	void btSalvar(ActionEvent event) {
		EstaticosDeAcesso.setFilial(cbxFiliaisPermitidas.getValue());
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	void preencheCombo() {
		PermissoesUsuarioFilial permissoes = new PermissoesUsuarioFilial();
		for (Filial filial : permissoes.listarFilial()) {
			cbxFiliaisPermitidas.getItems().add(filial);
		}
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

	public void setDialogStage(Stage dialogStage2) {
		this.dialogStage = dialogStage;

	}
}