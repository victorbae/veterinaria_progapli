package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.model.Raca;
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

	private RacaDao racaDao = new RacaBanco();
	private Raca racas;
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
		preencheRaca();
		racaDao.inserir(racas);

		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	public void preencheRaca() {
		racas = new Raca();
		racas.setNome(tfNome.getText());
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
