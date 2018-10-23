package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.FilialBanco;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.FilialDao;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Filial;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.Tipo_Animal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCadastroAnimais {
	@FXML
	private TextField tfNome;

	@FXML
	private DatePicker dtDataNascimento;

	@FXML
	private ComboBox<Raca> cbxRaca;

	@FXML
	private ComboBox<Cliente> cbxCliente;

	@FXML
	private ComboBox<Tipo_Animal> cbxTipoAnimal;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnCancelar;

	private Animais animais;

	private Stage dialogStage;

	private boolean clicadoSalvar;

	private ClienteDao clienteDao = new ClienteBanco();
	private Tipo_AnimalDao tipoAnimalDao = new Tipo_Animal;

	@FXML
	private void initialize() {
		populaCombo();
	}

	@FXML
	void Cancelar(ActionEvent event) {

	}

	@FXML
	void Limpar(ActionEvent event) {
		limpaTela();
	}

	@FXML
	void Salvar(ActionEvent event) {

	}

	public void limpaTela() {
		tfNome.clear();

		cbxRaca.getSelectionModel().clearSelection();
	}

	private void populaCombo() {
		for (Cliente filial : filialDao.listar()) {
			cbxFilial.getItems().add(filial);
		}
	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}
