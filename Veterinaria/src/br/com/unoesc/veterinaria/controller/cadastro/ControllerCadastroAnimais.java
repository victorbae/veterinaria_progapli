package br.com.unoesc.veterinaria.controller.cadastro;

import br.com.unoesc.veterinaria.banco.AnimaisBanco;
import br.com.unoesc.veterinaria.banco.ClienteBanco;
import br.com.unoesc.veterinaria.banco.RacaBanco;
import br.com.unoesc.veterinaria.banco.TipoAnimalBanco;
import br.com.unoesc.veterinaria.dao.AnimaisDao;
import br.com.unoesc.veterinaria.dao.ClienteDao;
import br.com.unoesc.veterinaria.dao.RacaDao;
import br.com.unoesc.veterinaria.dao.TipoAnimalDao;
import br.com.unoesc.veterinaria.dialogs.RacaDialogFactory;
import br.com.unoesc.veterinaria.dialogs.TipoAnimalDialogFactory;
import br.com.unoesc.veterinaria.model.Animais;
import br.com.unoesc.veterinaria.model.Cliente;
import br.com.unoesc.veterinaria.model.Raca;
import br.com.unoesc.veterinaria.model.TipoAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaAnimal;
import br.com.unoesc.veterinaria.staticos.auxiliares.EstaticosParaCliente;
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
	private ComboBox<TipoAnimal> cbxTipoAnimal;

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
	private TipoAnimalDao tipoAnimalDao = new TipoAnimalBanco();
	private RacaDao racaDao = new RacaBanco();

	@FXML
	private void initialize() {
		this.animais = EstaticosParaAnimal.animal;
		populaCombo();

		if (EstaticosParaAnimal.isEditando) {
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
		preencheAnimais();
		if (EstaticosParaAnimal.isEditando) {
			animaisDao.alterar(animais);
			EstaticosParaCliente.isEditando = false;
		} else {
			animaisDao.inserir(animais);
		}
		clicadoSalvar = true;
		if (dialogStage != null) {
			dialogStage.close();
		}
	}

	@FXML
	void OutraRaca(ActionEvent event) {
		Stage stageDono = (Stage) btnOutraRaca.getScene().getWindow();
		RacaDialogFactory adicionaRacaDialog = new RacaDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaRacaDialog.showDialog();

		if (clicadoSalvar) {
			dialogStage.close();

		}
	}

	public void populaTela() {
		tfNome.setText(animais.getNome());
		dtDataNascimento.setValue(animais.getData_Nascimento());
		cbxRaca.setValue(animais.getRaca());
		cbxCliente.setValue(animais.getCliente());
		cbxTipoAnimal.setValue(animais.getTipo_animal());
	}

	@FXML
	void OutroTipoAnimal(ActionEvent event) {
		Stage stageDono = (Stage) btnOutroTipoAnimal.getScene().getWindow();
		TipoAnimalDialogFactory adicionaTipoAnimalDialog = new TipoAnimalDialogFactory(stageDono);

		boolean clicadoSalvar = adicionaTipoAnimalDialog.showDialog();

		if (clicadoSalvar) {
			dialogStage.close();

		}
	}

	public void preencheAnimais() {
		animais = new Animais();
		animais.setNome(tfNome.getText());
		animais.setData_Nascimento(dtDataNascimento.getValue());
		animais.setRaca(EstaticosParaAnimal.achaRaca(cbxRaca.getValue().getIdRaca()));
		animais.setCliente(EstaticosParaCliente.achaCliente(cbxCliente.getValue().getIdCliente()));
		animais.setTipo_animal(EstaticosParaAnimal.achaTipoAnimal(cbxTipoAnimal.getValue().getIdTipoAnimal()));
	}

	public void limpaTela() {
		tfNome.clear();
		dtDataNascimento.setValue(null);
		cbxRaca.getSelectionModel().clearSelection();
		cbxCliente.getSelectionModel().clearSelection();
		cbxTipoAnimal.getSelectionModel().clearSelection();

	}

	private void populaCombo() {
		for (Cliente cliente : clienteDao.listar()) {
			cbxCliente.getItems().add(cliente);
		}
		for (Raca raca : racaDao.listar()) {
			cbxRaca.getItems().add(raca);
		}
		for (TipoAnimal tipoAnimal : tipoAnimalDao.listar()) {
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
