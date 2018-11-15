package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaTipoAnimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroTipoAnimal {
	@FXML
	private TextField tfNome;

	@FXML
	private ComboBox<Raca> cbxRaca;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	private TipoAnimal tipoAnimal;
	private Stage dialogStage;
	private boolean clicadoSalvar;
	private TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();
	private RacaDao racaDao = new RacaBanco();

	@FXML
	private void initialize() {
		this.tipoAnimal = EstaticosParaTipoAnimal.tipo_animal;
		populaCombo();

		if (EstaticosParaTipoAnimal.isEditando) {
			populaTela();
		}
	}

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
		preencheTipoAnimal();
		if (EstaticosParaTipoAnimal.isEditando) {
			tipoAnimalDao.alterar(tipoAnimal);
			EstaticosParaTipoAnimal.isEditando = false;
		} else {
			tipoAnimalDao.inserir(tipoAnimal);
		}
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	public void populaTela() {
		tfNome.setText(tipoAnimal.getNome());

	}

	public void preencheTipoAnimal() {
		tipoAnimal = new TipoAnimal();
		tipoAnimal.setNome(tfNome.getText());

	}

	public void limpaTela() {
		tfNome.clear();
		cbxRaca.getSelectionModel().clearSelection();

	}

	private void populaCombo() {
		for (Raca raca : racaDao.listar()) {
			cbxRaca.getItems().add(raca);
		}
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}
}
