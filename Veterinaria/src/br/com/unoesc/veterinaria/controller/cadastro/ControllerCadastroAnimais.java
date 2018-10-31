package br.com.unoesc.veterinaria.controller.cadastro;

import java.sql.Date;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.Tipo_AnimalBanco;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.Tipo_AnimalDao;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.Tipo_Animal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;
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
	private Button btnOutraRaca;

	@FXML
	private Button btnOutroTipoAnimal;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnCancelar;

	private Animais animais;

	private Stage dialogStage;

	private boolean clicadoSalvar;
	private AnimaisDao animaisDao = new AnimaisBanco();
	private ClienteDao clienteDao = new ClienteBanco();
	private Tipo_AnimalDao tipoAnimalDao = new Tipo_AnimalBanco();
	private RacaDao racaDao = new RacaBanco();

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
		preencheAnimais();
		animaisDao.inserir(animais);

		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	@FXML
	void OutraRaca(ActionEvent event) {

	}

	@FXML
	void OutroTipoAnimal(ActionEvent event) {

	}

	public void preencheAnimais() {
		animais = new Animais();
		animais.setNome(tfNome.getText());
		animais.setData_Nascimento(Date.valueOf(dtDataNascimento.getValue()));
		animais.setRaca(EstaticosParaAnimal.achaRaca(cbxRaca.getValue().getIdRaca()));
		animais.setCliente(EstaticosParaAnimal.achaCliente(cbxCliente.getValue().getIdCliente()));
	}

	public void limpaTela() {
		tfNome.clear();
		cbxRaca.getSelectionModel().clearSelection();
		cbxCliente.getSelectionModel().clearSelection();
		cbxTipoAnimal.getSelectionModel().clearSelection();
		dtDataNascimento.setValue(null);
	}

	private void populaCombo() {
		for (Cliente cliente : clienteDao.listar()) {
			cbxCliente.getItems().add(cliente);
		}
		for (Raca raca : racaDao.listar()) {
			cbxRaca.getItems().add(raca);
		}
		for (Tipo_Animal tipoAnimal : tipoAnimalDao.listar()) {
			cbxTipoAnimal.getItems().add(tipoAnimal);
		}

	}

	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean clicadoSalvar() {
		return clicadoSalvar;
	}

}
