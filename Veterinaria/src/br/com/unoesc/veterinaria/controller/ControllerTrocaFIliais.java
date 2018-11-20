package br.com.unoesc.veterinaria.controller;

import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosDeAcesso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ControllerTrocaFIliais {

	@FXML
	private ComboBox<Filial> cbxFiliaisPermitidas;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	@FXML
	void btCancelar(ActionEvent event) {
		dialogStage.close();
	}

	@FXML
	void btSalvar(ActionEvent event) {
		EstaticosDeAcesso.setFilial(cbxFiliaisPermitidas.getValue());
		clicadoSalvar = true;
		dialogStage.close();
	}

//	void preencheCombo() {
//		PermissoesBanco permissoes = null;
//		for (Filial filial : permissoes.getFiliaisByPermissoesDoFuncionario(EstaticosDeAcesso.getFuncionario())) {
//			cbxFiliaisPermitidas.getItems().add(filial);
//		}
//	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}