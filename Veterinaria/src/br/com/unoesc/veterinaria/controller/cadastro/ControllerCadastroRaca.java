package br.com.unoesc.veterinaria.controller.cadastro;

import org.controlsfx.control.textfield.TextFields;

import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaRaca;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaTipoAnimal;
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

	@FXML
	private TextField tfTipoAnimal;

	private RacaDao racaDao = new RacaBanco();
	private TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();
	private Raca raca;
	private Stage dialogStage;
	private boolean clicadoSalvar;

	@FXML
	private void initialize() {

		if (EstaticosParaRaca.isEditando) {
			raca = EstaticosParaRaca.raca;
			populaTela();
		}

		TextFields.bindAutoCompletion(tfTipoAnimal, tipoAnimalDao.listar());
	}

	@FXML
	void cancelar(ActionEvent event) {
		limpaTela();
		dialogStage.close();
	}

	@FXML
	void limpar(ActionEvent event) {
		limpaTela();
	}

	@FXML
	void salvar(ActionEvent event) {
		populaRaca();
		if (EstaticosParaRaca.isEditando) {
			racaDao.alterar(raca);
			EstaticosParaRaca.isEditando = false;
		} else {
			racaDao.inserir(raca);
		}
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	public void populaTela() {
		tfNome.setText(raca.getNome());
		tfTipoAnimal.setText(raca.getTipoAnimal().getNome());
	}

	public void populaRaca() {
		raca = new Raca();
		raca.setNome(tfNome.getText());
		raca.setTipoAnimal(EstaticosParaTipoAnimal.achaTipoAnimalByNome(tfTipoAnimal.getText()));
	}

	public void limpaTela() {
		tfNome.clear();
		tfTipoAnimal.clear();

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}
